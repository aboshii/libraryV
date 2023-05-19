package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.BookNotFoundException;
import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.User;
import com.library.springlibrary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Optional<Set<Book>> getUserBooksByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User finalUser = optionalUser.get();
            Set<Book> borrowedBooks = finalUser.getBorrowedBooks();
            return Optional.of(borrowedBooks);
        } else {
            return Optional.empty();
        }
    }
    public User getUserById(Long id){
        System.out.println("getuserlog");
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public ArrayList<User> getUsers(){
        System.out.println("getuserslog");
        return (ArrayList) userRepository.findAll();
    }
}
