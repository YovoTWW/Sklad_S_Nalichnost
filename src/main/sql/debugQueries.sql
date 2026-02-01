DELETE FROM "Client";
DELETE FROM Invoice;
DELETE FROM Paydesk;
DELETE FROM Stock;
DELETE FROM "Storage";
DELETE FROM Supplier;
DELETE FROM SupplierStock;

DROP TABLE "Client";
DROP TABLE Invoice;
DROP TABLE Paydesk;
DROP TABLE Stock;
DROP TABLE "Storage";
DROP TABLE Supplier;
DROP TABLE SupplierStock;

SELECT * FROM "Client";
SELECT * FROM Invoice;
SELECT * FROM Paydesk;
SELECT * FROM Stock;
SELECT * FROM "Storage";
SELECT * FROM Supplier;
SELECT * FROM SupplierStock;

COMMIT;