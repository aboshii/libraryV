CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       publicationYear DATE,
                       publisher VARCHAR(255),
                       authorFirstName VARCHAR(255),
                       authorLastName VARCHAR(255),
                       isbn VARCHAR(255)
);