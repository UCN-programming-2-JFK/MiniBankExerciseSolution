-- for cleaning up after each run of the code, to avoid unique constraint violations
use Minibank01
go

DELETE FROM PostalCity WHERE postalCode = '7500'
DELETE FROM Account WHERE accNo = 42
DELETE FROM Account WHERE accNo = 30

SELECT * FROM Account