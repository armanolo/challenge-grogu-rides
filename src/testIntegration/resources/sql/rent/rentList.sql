INSERT INTO public.vehicles (id,seats) VALUES
('1c7fcfd7-423b-4742-a20c-49cba845bf2f',5),
('3e07217f-6f1c-491b-af9e-ba1e936b2d5f',4),
('c02530b9-2082-4706-a8f3-f91b7ddecb79',3);

INSERT INTO public.users (id,dni,name) VALUES
('b71ee839-bcb4-4a86-9e62-1e8df0683c8c','45722533M','Manolo Martin'),
('ba200940-3c0e-4489-974e-fdb1405aca7f','91549421Z','Miguel Duato');

INSERT INTO public.rents (id, user_id, vehicle_id,end_time,start_time) VALUES
('0f60fcc9-f104-4dce-93c4-ea2648fb3571','b71ee839-bcb4-4a86-9e62-1e8df0683c8c',
    '1c7fcfd7-423b-4742-a20c-49cba845bf2f',(now()::timestamp + interval '2 hour'),now()::timestamp),
('0142e57c-6ec5-44ae-9689-73d740cddcbb','ba200940-3c0e-4489-974e-fdb1405aca7f',
    'c02530b9-2082-4706-a8f3-f91b7ddecb79',(now()::timestamp + interval '2 hour'),now()::timestamp);
