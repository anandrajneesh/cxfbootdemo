package org.blog.service;

import org.blog.dao.UserRepository;
import org.blog.exceptions.ResourceAlreadyExistsException;
import org.blog.exceptions.ResourceException;
import org.blog.exceptions.ResourceNotFoundException;
import org.blog.exceptions.ValidationException;
import org.blog.models.User;
import org.blog.utils.Validation;
import org.blog.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.*;

/**
 * Created by Anand_Rajneesh on 3/30/2017.
 */
@Component
public class UserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacade.class);

    @Autowired
    private UserRepository userRepository;

    public User create(User newUser) throws ValidationException, ResourceAlreadyExistsException {
        Validation objNotNull = validation(() -> newUser, Validator::notNull, ValidationException::new);
        Validation emailNotNull = validation(newUser::getEmail, Validator::notNull, ValidationException::new);
        Validation doesNotExists = validation(newUser::getEmail, (email) -> !Optional.ofNullable(userRepository.findByEmail(email)).isPresent(), ResourceAlreadyExistsException::new);
        Validation[] validations = {objNotNull, emailNotNull, doesNotExists};
        UnaryOperator<User> save = (user) -> userRepository.save(user);
        return run(newUser, validations, save);
    }

    public User get(String id) throws ValidationException, ResourceNotFoundException {
        Validation idNotNull = validation(() -> id, Validator::notNull, ValidationException::new);
        Validation[] validations = {idNotNull};
        Function<String, User> get = (arg) -> Optional.ofNullable(userRepository.findOne(arg)).orElseThrow(ResourceNotFoundException::new);
        return run(id, validations, get);
    }

    public User modifyDetails(User user) throws ValidationException, ResourceNotFoundException {
        Validation objNotNull = validation(() -> user, Validator::notNull, ValidationException::new);
        Validation idNotNull = validation(user::getId, Validator::notNull, ValidationException::new);
        Validation exists = validation(user::getId, (id) -> userRepository.exists(id), ResourceNotFoundException::new);
        Validation[] validations = {objNotNull, idNotNull, exists};
        UnaryOperator<User> update = (modifiedUser) -> userRepository.save(modifiedUser);
        return run(user, validations, update);
    }

    public void delete(String id) throws ValidationException, ResourceNotFoundException {
        Validation idNotNull = validation(() -> id, Validator::notNull, ValidationException::new);
        Validation exists = validation(() -> id, (arg) -> userRepository.exists(arg), ResourceNotFoundException::new);
        Consumer<String> delete = (arg) -> userRepository.delete(arg);
        Validation[] validations = {idNotNull, exists};
        run(id, validations, delete);
    }

    public <E extends ResourceException, T> Validation validation(Supplier<T> supplier, Predicate<T> predicate, Supplier<E> exceptionSupplier) {
        return () -> {
            boolean success = predicate.test(supplier.get());
            if (!success) {
                throw exceptionSupplier.get();
            }
        };
    }

    /**
     * @param t
     * @param validations - Fail fast validations
     * @param operator
     * @param <T>
     * @param <R>
     * @return
     */
    public <T, R> R run(T t, Validation[] validations, Function<T, R> operator) {
        for (Validation validation : validations) {
            validation.check();
        }
        return operator.apply(t);
    }

    public <T> void run(T t, Validation[] validations, Consumer<T> operator) {
        Function<T, Void> wrapper = (arg) -> {
            operator.accept(arg);
            return null;
        };
        run(t, validations, wrapper);
    }

    public <T, R> R run(T t, Function<T, R> operator) {
        return run(t, new Validation[]{}, operator);
    }

}
