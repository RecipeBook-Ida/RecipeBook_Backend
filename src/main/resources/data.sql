INSERT INTO app_user (username, firstname, lastname, image) VALUES ('idap', 'Ida', 'L', 'https://res.cloudinary.com/norgesgruppen/images/c_scale,dpr_auto,f_auto,q_auto:eco,w_auto/b4golm8sdghdq1vzivxz/klassisk-pasta-carbonara');
INSERT INTO app_user (username, firstname, lastname) VALUES ('id', 'Ida', 'k');

INSERT INTO recipe (title, description, cooktime, image, cuisine, type, portion, user_id)
VALUES ('Spaghetti Carbonara', 'Classic Italian pasta dish with bacon, eggs, and cheese.',
        20, 'https://res.cloudinary.com/norgesgruppen/images/c_scale,dpr_auto,f_auto,q_auto:eco,w_auto/b4golm8sdghdq1vzivxz/klassisk-pasta-carbonara', 'Italian', 'Dinner', 4, 1);

INSERT INTO recipe (title, description, cooktime, image, cuisine, type, portion, user_id)
VALUES ('Chicken Fajitas', 'Tender chicken strips with colorful bell peppers and onions, seasoned with fajita spices.',
        25, 'https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2020/02/Chicken-Fajitas-7.jpg', 'Mexican', 'Dinner', 4, 1);

INSERT INTO recipe (title, description, cooktime,  cuisine, type, portion, user_id)
VALUES ('Vegetable Curry', 'A flavorful vegetarian curry made with mixed vegetables and aromatic spices.',
        30,  'Vegetarian', 'Dinner', 4, 1);

INSERT INTO recipe (title, description, cooktime, image, cuisine, type, portion, user_id)
VALUES ('Grilled Salmon with Lemon Butter Sauce', 'Delicious grilled salmon fillets topped with a tangy lemon butter sauce.',
        15, 'https://example.com/grilled-salmon.jpg', 'Seafood', 'Dinner', 4, 1);

INSERT INTO recipe (title, description, cooktime,  cuisine, type, portion, user_id)
VALUES ('Caprese Salad', 'A simple and refreshing salad made with ripe tomatoes, fresh mozzarella, and fragrant basil.',
        10,  'Salad', 'Lunch', 2, 1);

INSERT INTO recipe (title, description, cooktime, cuisine, type, portion, user_id)
VALUES ('Chocolate Lava Cake', 'Indulgent chocolate cakes with a gooey, molten center that oozes out when cut.',
        20,  'Dessert', 'Dessert', 4, 1);

INSERT INTO sub_recipe (title, instructions, recipe_id)
VALUES ('Spaghetti Carbonara Instructions', 'Cook spaghetti according to package instructions. \nIn a skillet, cook bacon until crispy. \nIn a bowl, whisk together eggs, parmesan cheese, and black pepper. \nDrain spaghetti and add to the skillet with the bacon. \nRemove skillet from heat and quickly stir in egg mixture, allowing the residual heat to cook the eggs and create a creamy sauce. \nServe immediately, garnished with additional parmesan cheese and black pepper.', 1);
INSERT INTO sub_recipe (title, instructions, recipe_id)
VALUES ('Spaghetti Carbonara Instructions', 'Cook spaghetti according to package instructions. \nIn a skillet, cook bacon until crispy. \nIn a bowl, whisk together eggs, parmesan cheese, and black pepper. \nDrain spaghetti and add to the skillet with the bacon. \nRemove skillet from heat and quickly stir in egg mixture, allowing the residual heat to cook the eggs and create a creamy sauce. \nServe immediately, garnished with additional parmesan cheese and black pepper.', 1);

INSERT INTO sub_recipe (title, instructions, recipe_id)
VALUES ('Chicken Fajitas Instructions', 'Slice chicken breast into thin strips and marinate in fajita seasoning for 30 minutes. \nHeat oil in a skillet over medium-high heat. \nAdd marinated chicken strips and cook until browned and cooked through. \nRemove chicken from skillet and set aside. \nIn the same skillet, add sliced bell peppers and onions. Cook until softened and slightly charred. \nReturn cooked chicken to the skillet and toss with the peppers and onions. \nServe hot with tortillas, salsa, guacamole, and sour cream.', 2);

INSERT INTO sub_recipe (title, instructions, recipe_id)
VALUES ('Vegetable Curry Instructions', 'Heat oil in a large pot over medium heat. \nAdd chopped onions, garlic, and ginger. Sauté until onions are translucent. \nStir in curry paste and cook for 1 minute. \nAdd chopped vegetables (such as potatoes, carrots, cauliflower, and peas) to the pot. \nPour in coconut milk and vegetable broth. Bring to a simmer and cook until vegetables are tender. \nSeason with salt, pepper, and lime juice to taste. \nServe hot with rice or naan bread.', 3);

INSERT INTO sub_recipe (title, instructions, recipe_id)
VALUES ('Grilled Salmon Instructions', 'Preheat grill to medium-high heat. \nSeason salmon fillets with salt, pepper, and a squeeze of lemon juice. \nGrill salmon for 4-5 minutes per side, or until cooked through. \nIn a small saucepan, melt butter over low heat. \nStir in lemon zest, lemon juice, and minced garlic. \nCook for 1-2 minutes, until fragrant. \nRemove grilled salmon from the grill and drizzle with the lemon butter sauce. \nServe hot with your favorite side dishes.', 4);

INSERT INTO sub_recipe (title, instructions,recipe_id)
VALUES ('Caprese Salad Instructions', 'Slice ripe tomatoes and fresh mozzarella into 1/4-inch thick slices. \nArrange tomato and mozzarella slices on a serving platter, alternating between them. \nTuck fresh basil leaves between the tomato and mozzarella slices. \nDrizzle extra virgin olive oil over the salad. \nSprinkle with balsamic glaze, salt, and pepper. \nServe immediately as a light appetizer or side dish.', 5);

INSERT INTO sub_recipe (title, instructions, recipe_id)
VALUES ('Chocolate Lava Cake Instructions', 'Preheat oven to 425°F (220°C). \nGrease four ramekins with butter and dust with cocoa powder. \nIn a microwave-safe bowl, melt chocolate and butter together in 30-second intervals, stirring until smooth. \nStir in powdered sugar, flour, and eggs until well combined. \nDivide batter evenly among the prepared ramekins. \nBake for 12-14 minutes, or until the edges are set but the center is still soft. \nCarefully invert each cake onto a plate. \nServe immediately with a scoop of vanilla ice cream.', 6);


-- Insert data into the 'ingredient' table
INSERT INTO ingredient (name, image, category) VALUES
                                                   ('Onion', 'onion_image.jpg', 'italiensk'),
                                                   ('Basil', 'basil_image.jpg', 'italiensk'),
                                                   ('Mozzarella', 'mozzarella_image.jpg', 'italiensk'),
                                                   ('Olive Oil', 'olive_oil_image.jpg', 'italiensk'),
                                                   ('Pasta', 'pasta_image.jpg', 'italiensk'),
                                                   ('Parmesan Cheese', 'parmesan_cheese_image.jpg', 'italiensk');

-- Insert data into the 'ingredient_quantity' table
INSERT INTO ingredient_quantity (quantity, unit, ingredient_id) VALUES
                                                                    (1, 'stk', 3), -- Mozzarella
                                                                    (3, 'spsk', 4), -- Olive Oil
                                                                    (200, 'g', 5), -- Pasta
                                                                    (50, 'g', 6); -- Parmesan Cheese

-- Insert data into the 'recipe_ingredients' table
INSERT INTO recipe_ingredients (sub_recipe_id, ingredient_quantity_id) VALUES
                                                                       (1, 1), -- Mozzarella
                                                                       (1, 2), -- Olive Oil
                                                                       (1, 3), -- Pasta
                                                                       (1, 4), -- Parmesan Cheese
                                                                       (2, 1), -- Mozzarella
                                                                       (2, 2), -- Olive Oil
                                                                       (2, 3), -- Pasta
                                                                       (2, 4); -- Parmesan Cheese
