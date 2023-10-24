INSERT INTO public.vehicles (id,seats) VALUES
('1c7fcfd7-423b-4742-a20c-49cba845bf2f',5);

INSERT INTO public.users (id,dni,name) VALUES
('b71ee839-bcb4-4a86-9e62-1e8df0683c8c','45722533M','Manolo Martin'),
('32281f54-6424-4047-8410-884ab69d5eeb','20716154P','Carmen Ruiz');

INSERT INTO public.rents (id, user_id, vehicle_id,end_time,start_time) VALUES
('0f60fcc9-f104-4dce-93c4-ea2648fb3571','b71ee839-bcb4-4a86-9e62-1e8df0683c8c',
    '1c7fcfd7-423b-4742-a20c-49cba845bf2f',(now()::timestamp + interval '2 hour'),now()::timestamp);
