INSERT INTO vehicles (id,seats) VALUES
('a78ccf93-582d-4277-a875-f99e92071bfc',2),
('c226f69a-0f14-464d-a29d-4af37b158377',3);

INSERT INTO users (id,dni,"name") VALUES
('6979d13f-e15d-4ef1-b4ea-a8c5afc1d341','10090990R','Lucius Gleichner'),
('dbab49a1-a4ae-4b14-bc49-6b37250ae250','60735247H','Kaleb Rippin'),
('743f4b18-629c-4bf1-9041-3455d8f5e647','75170711F','Brandon Bernier');

INSERT INTO rents (id,end_time,return_time,start_time,user_id,vehicle_id) VALUES
('c0ccf646-31e5-43fe-a99e-313dad3e4aca','2023-08-06 15:51:00',NULL,'2023-08-06 13:51:13.410811',
    'dbab49a1-a4ae-4b14-bc49-6b37250ae250','c226f69a-0f14-464d-a29d-4af37b158377'),
('bde6a5a3-221b-414e-b8ac-5e5930a63c58','2023-08-06 15:51:00','2023-08-06 13:51:22.183386',
    '2023-08-06 13:51:11.058249','6979d13f-e15d-4ef1-b4ea-a8c5afc1d341','a78ccf93-582d-4277-a875-f99e92071bfc'),
('62e5383a-f15a-4c86-a56c-7d184241b725','2023-08-06 15:51:00',NULL,'2023-08-06 13:51:27.464825',
    '743f4b18-629c-4bf1-9041-3455d8f5e647','a78ccf93-582d-4277-a875-f99e92071bfc');
