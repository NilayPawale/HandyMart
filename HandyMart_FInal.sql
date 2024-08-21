CREATE TABLE Login (
    login_id INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL 
);

-- ProductCategory table
CREATE TABLE ProductCategory (
    Category_ID INT AUTO_INCREMENT PRIMARY KEY,
    Category_name VARCHAR(100),
    Active_flag TINYINT
);

INSERT INTO ProductCategory (Category_ID, Category_name, Active_flag)
VALUES
    (1, 'Food', 1),
    (2, 'Clothes', 1),
    (3, 'Accessories', 1),
    (4, 'Crafts', 1),
    (5, 'Footwear', 1),
    (6, 'Toys', 1);

-- SubProductCategory table
CREATE TABLE SubProductCategory (
    SubCategory_ID INT AUTO_INCREMENT PRIMARY KEY,
    Category_ID INT,
    SubCategory_name VARCHAR(100),
    Active_flag TINYINT,
    FOREIGN KEY (Category_ID) REFERENCES ProductCategory(Category_ID)
);

INSERT INTO SubProductCategory (Category_ID, SubCategory_name, Active_flag)
VALUES
    (1, 'Papad', 1),
    (1, 'Snacks', 1),
    (4, 'Candles', 1),
    (4, 'Cards', 1);

-- Vendors table
CREATE TABLE Vendors (
    vendor_id INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Phone VARCHAR(20) NOT NULL,
    AadhaarNumber VARCHAR(20) NOT NULL UNIQUE,
    login_id INT,
    FOREIGN KEY (login_id) REFERENCES Login(login_id)
);

-- VendorProducts table
CREATE TABLE VendorProducts (
    vendor_product_id INT AUTO_INCREMENT PRIMARY KEY,
    vendor_id INT,
    category_id INT,
    subcategory_id INT,
    ProductName VARCHAR(100) NOT NULL,
    Description VARCHAR(200),
    Price DECIMAL(10,2) NOT NULL,
    Inventory INT NOT NULL,
    Active_flag TINYINT,
    Image BLOB, -- Use BLOB for storing images
    FOREIGN KEY (vendor_id) REFERENCES Vendors(vendor_id),
    FOREIGN KEY (category_id) REFERENCES ProductCategory(Category_ID),
    FOREIGN KEY (subcategory_id) REFERENCES SubProductCategory(SubCategory_ID)
);