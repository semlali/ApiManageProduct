-- Insérer des données dans la table users
INSERT INTO users (id, username, firstname, email, password) VALUES
                                                                 (1, 'jdoe', 'John', 'jdoe@example.com', '$2a$10$7QJz5e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9'), -- Mot de passe encodé
                                                                 (2, 'asmith', 'Alice', 'asmith@example.com', '$2a$10$7QJz5e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9e8h1J9');

-- Insérer des données dans la table product
INSERT INTO product
    (id, code, name, description, image, category, price, quantity, internalReference,
     shellId, inventoryStatus, rating, createdAt, updatedAt)
VALUES
                                                                                                                                                                   (1, 'P001', 'Product 1', 'Description for product 1', 'image1.jpg', 'Category 1', 19.99, 100, 'REF001', 1, 'INSTOCK', 5, NOW(), NOW()),
                                                                                                                                                                   (2, 'P002', 'Product 2', 'Description for product 2', 'image2.jpg', 'Category 2', 29.99, 50, 'REF002', 2, 'LOWSTOCK', 4, NOW(), NOW()),
                                                                                                                                                                   (3, 'P003', 'Product 3', 'Description for product 3', 'image3.jpg', 'Category 3', 9.99, 0, 'REF003', 3, 'OUTOFSTOCK', 3, NOW(), NOW());
