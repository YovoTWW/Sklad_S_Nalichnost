-- Storage
INSERT INTO "Storage" 
    VALUES ('e0138e35-7354-49d5-9c29-bd277dfccf5c');

-- Paydesk
INSERT INTO Paydesk 
    VALUES ('e0138e35-7354-49d5-9c29-bd277dfccf5c', 10000);

-- Clients
INSERT INTO "Client" 
    VALUES ('3f476571-5a45-43b4-916b-1ebe5b11eb83', 'Ivan');
INSERT INTO "Client" 
    VALUES ('2a61f2ba-7483-4541-9644-eca9571f18ee', 'Maria');

-- Suppliers
INSERT INTO Supplier 
    VALUES ('f855ec26-928a-43db-8c77-7ecd84533c33', 'ABC Ltd');
INSERT INTO Supplier 
    VALUES ('c98dc49a-7c11-4282-a56f-822c238b4f4b', 'XYZ Corp');

-- Stock
INSERT INTO Stock
    VALUES ('aad2b006-393a-4f05-91a1-5dadcf438782', 'Beer', 1.00, 1.50, 100, 'e0138e35-7354-49d5-9c29-bd277dfccf5c');
INSERT INTO Stock
    VALUES ('f874f505-b72b-4924-b843-c71bed7a266a', 'Wine', 3.00, 5.00, 50, 'e0138e35-7354-49d5-9c29-bd277dfccf5c');
    
INSERT INTO SupplierStock
    VALUES ('f855ec26-928a-43db-8c77-7ecd84533c33', 'aad2b006-393a-4f05-91a1-5dadcf438782', 25);
INSERT INTO SupplierStock
    VALUES ('f855ec26-928a-43db-8c77-7ecd84533c33', 'f874f505-b72b-4924-b843-c71bed7a266a', 50);
INSERT INTO SupplierStock
    VALUES ('c98dc49a-7c11-4282-a56f-822c238b4f4b', 'aad2b006-393a-4f05-91a1-5dadcf438782', 75);
    
COMMIT;