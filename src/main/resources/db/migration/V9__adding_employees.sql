WITH inserted_addresses AS (
INSERT INTO address (street, city, state, postcode, country, lat, lng)
VALUES
    ('Płocka', 'Warszawa', 'Mazowieckie', '01-231', 'Poland', 52.2361739, 20.9652506),
    ('Piotrkowska', 'Łódź', 'Łódzkie', '90-004', 'Poland', 51.7749211, 19.45532),
    ('Odlewnicza', 'Warszawa', 'Mazowieckie', '03-231', 'Poland', 52.3041412, 21.0129186),
    ('Kubusia Puchatka', 'Warszawa', 'Mazowieckie', '00-037', 'Poland', 52.2361155, 21.0167448),
    ('Morska', 'Gdynia', 'Pomorskie', '81-222', 'Poland', 54.5299276, 18.4994902),
    ('Mazowiecka', 'Warszawa', 'Mazowieckie', '00-052', 'Poland', 52.2371415, 21.0128924),
    ('Warszawska', 'Radzyń Podlaski', 'Lubelskie', '21-300', 'Poland', 51.7823025, 22.6157933),
    ('Opolan', 'Lublin', 'Lubelskie', '20-828', 'Poland', 51.2588892, 22.4947042),
    ('Krupówki', 'Zakopane', 'Małopolskie', '34-500', 'Poland', 49.2950977, 19.9509576),
    ('Wiesława Zarzyckiego', 'Kraków', 'Małopolskie', '30-898', 'Poland', 50.0168678, 20.0576491),
    ('Batorego', 'Tychy', 'Województwo Śląskie', '43-100', 'Poland', 50.1268806, 18.9789303),
    ('Marszałkowska', 'Warszawa', 'Mazowieckie', '00-026', 'Poland', 52.2142312, 21.0215712),
    ('Lwowska', 'Rzeszów', 'Podkarpackie', '35-301', 'Poland', 50.038081, 22.0399635),
    ('Leszka Białego', 'Łódź', 'Łódzkie', '92-402', 'Poland', 51.7402545, 19.5642647),
    ('Piernikarczyka', 'Tarnowskie Góry', 'Województwo Śląskie', '42-600', 'Poland', 50.4348744, 18.8561289),
    ('Poznańska', 'Bydgoszcz', 'Kujawsko-Pomorskie', '85-129', 'Poland', 53.1214357, 17.9948744),
    ('Szpitalna', 'Suwałki', 'Podlaskie', '16-402', 'Poland', 54.1202, 22.9218225),
    ('Bałtycka', 'Koszalin', 'Zachodniopomorskie', '75-331', 'Poland', 54.1987947, 16.1827196),
    ('Mickiewicza', 'Jastarnia', 'Pomorskie', '84-140', 'Poland', 54.6995966, 18.6724748),
    ('Starowiejska', 'Gdynia', 'Pomorskie', '81-356', 'Poland', 54.5213928, 18.54171),
    ('Oficerska', 'Mrągowo', 'Warmińsko-Mazurskie', '11-700', 'Poland', 53.8648542, 21.3076913),
    ('Szybka', 'Wrocław', 'Dolnośląskie', '50-420', 'Poland', 51.1023662, 17.0532519),
    ('Wilczak', 'Poznań', 'Wielkopolskie', '61-001', 'Poland', 52.430942, 16.9521113),
    ('Podgórna', 'Elbląg', 'Warmińsko-Mazurskie', '82-300', 'Poland', 54.1841406, 19.3979304),
    ('Marszałkowska', 'Warszawa', 'Mazowieckie', '00-590', 'Poland', 52.21447089999999, 21.0214633),
    ('Władysława IV', 'Zielona Góra', 'Lubuskie', '65-229', 'Poland', 51.9381264, 15.5275663),
    ('Wiejska', 'Białystok', 'Podlaskie', '15-351', 'Poland', 53.119572, 23.1437614),
    ('Stalowa', 'Wrocław', 'Dolnośląskie', '53-425', 'Poland', 51.09380179999999, 17.002841),
    ('Lotnicza', 'Radom', 'Mazowieckie', '26-603', 'Poland', 51.3924281, 21.1964283),
    ('Pana Tadeusza', 'Olsztyn', 'Warmińsko-Mazurskie', '10-001', 'Poland', 53.7757906, 20.5068616),
    ('Kolonistów', 'Szczecin', 'Zachodniopomorskie', '71-804', 'Poland', 53.4884805, 14.5793539),
    ('plac Politechniki', 'Warszawa', 'Mazowieckie', '00-661', 'Poland', 52.220524, 21.010564),
    ('Ursynowska', 'Warszawa', 'Mazowieckie', '02-605', 'Poland', 52.1973104, 21.0174906),
    ('Wilanowska', 'Wrocław', 'Dolnośląskie', '51-206', 'Poland', 51.1548376, 17.128509),
    ('Zamojska', 'Garwolin', 'Mazowieckie', '08-400', 'Poland', 51.9052589, 21.6027231),
    ('Wincentego z Kielc', 'Kielce', 'Świętokrzyskie', '25-001', 'Poland', 50.9060005, 20.665357),
    ('Andrzeja Frycza-Modrzewskiego', 'Kraków', 'Małopolskie', '31-216', 'Poland', 50.0869603, 19.9454303),
    ('św. Andrzeja Boboli', 'Warszawa', 'Mazowieckie', '02-525', 'Poland', 52.2049773, 20.9992516),
    ('Lawendowa', 'Ciechanów', 'Mazowieckie', '06-400', 'Poland', 52.8769845, 20.5915286),
    ('Wałowa', 'Gdańsk', 'Pomorskie', '80-858', 'Poland', 54.3583262, 18.6539235)
    RETURNING id
    )
INSERT INTO employee_table
(name, email, password, position, squad, department, address_Id, user_role, is_enabled)
VALUES
   ('Alice Johnson', 'alice.johnson@gmail.com', 'pass', 'Product Manager', 'Alpha', 'Product Management', (SELECT id FROM inserted_addresses LIMIT 1), 'USER', true),
    ('Bob Smith', 'bob.smith@example.com', 'pass', 'Software Engineer', 'Beta', 'Development', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 1), 'USER', true),
    ('Charlie Brown', 'charlie.brown@o2.pl', 'pass', 'Data Scientist', 'Gamma', 'Analytics', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 2), 'USER', true),
    ('David Wilson', 'david.wilson@gmail.com', 'pass', 'System Administrator', 'Delta', 'IT Support', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 3), 'USER', true),
    ('Emma Miller', 'emma.miller@example.com', 'pass', 'UX Designer', 'Alpha', 'Design', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 4), 'USER', true),
    ('Frank Turner', 'frank.turner@o2.pl', 'pass', 'Network Engineer', 'Beta', 'IT Infrastructure', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 5), 'USER', true),
    ('Grace Harris', 'grace.harris@gmail.com', 'pass', 'Business Analyst', 'Gamma', 'Business Analysis', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 6), 'USER', true),
    ('Harry Evans', 'harry.evans@example.com', 'pass', 'Database Administrator', 'Delta', 'Database Management', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 7), 'USER', true),
    ('Isabel Parker', 'isabel.parker@o2.pl', 'pass', 'Security Specialist', 'Alpha', 'Security', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 8), 'USER', true),
    ('Jack Turner', 'jack.turner@gmail.com', 'pass', 'UI Developer', 'Beta', 'Development', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 9), 'USER', true),
    ('John Doe', 'john.doe@example.com', 'pass', 'Developer', 'Gamma', 'IT', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 10), 'USER', true),
    ('Jane Johnson', 'jane.johnson@gmail.com', 'pass', 'Product Owner', 'Delta', 'Product Management', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 11), 'USER', true),
    ('Kevin Smith', 'kevin.smith@example.com', 'pass', 'Data Engineer', 'Alpha', 'Analytics', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 12), 'USER', true),
    ('Linda Brown', 'linda.brown@o2.pl', 'pass', 'Software Tester', 'Beta', 'Quality Assurance', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 13), 'USER', true),
    ('Michael Wilson', 'michael.wilson@gmail.com', 'pass', 'DevOps Engineer', 'Gamma', 'IT Infrastructure', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 14), 'USER', true),
    ('Nancy Miller', 'nancy.miller@example.com', 'pass', 'Technical Writer', 'Delta', 'Documentation', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 15), 'USER', true),
    ('Oliver Parker', 'oliver.parker@o2.pl', 'pass', 'Network Administrator', 'Alpha', 'IT Support', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 16), 'USER', true),
    ('Penelope Turner', 'penelope.turner@gmail.com', 'pass', 'UX Researcher', 'Beta', 'Design', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 17), 'USER', true),
    ('Quincy Harris', 'quincy.harris@example.com', 'pass', 'Business Intelligence Analyst', 'Gamma', 'Business Analysis', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 18), 'USER', true),
    ('Richard Evans', 'richard.evans@o2.pl', 'pass', 'Security Analyst', 'Delta', 'Security', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 19), 'USER', true),
    ('Samuel Adams', 'samuel.adams@gmail.com', 'pass', 'Data Analyst', 'Alpha', 'Analytics', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 20), 'USER', true),
    ('Tiffany Taylor', 'tiffany.taylor@example.com', 'pass', 'Software Developer', 'Beta', 'Development', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 21), 'USER', true),
    ('Ursula Brown', 'ursula.brown@o2.pl', 'pass', 'UX/UI Designer', 'Gamma', 'Design', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 22), 'USER', true),
    ('Victor Turner', 'victor.turner@gmail.com', 'pass', 'System Architect', 'Delta', 'IT Infrastructure', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 23), 'USER', true),
    ('Wendy Wilson', 'wendy.wilson@example.com', 'pass', 'Quality Assurance Engineer', 'Alpha', 'Quality Assurance', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 24), 'USER', true),
    ('Xander Evans', 'xander.evans@o2.pl', 'pass', 'IT Support Specialist', 'Beta', 'IT Support', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 25), 'USER', true),
    ('Yvonne Harris', 'yvonne.harris@gmail.com', 'pass', 'Product Analyst', 'Gamma', 'Product Management', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 26), 'USER', true),
    ('Zane Miller', 'zane.miller@example.com', 'pass', 'Database Developer', 'Delta', 'Database Management', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 27), 'USER', true),
    ('Alex Johnson', 'alex.johnson@gmail.com', 'pass', 'Network Security Engineer', 'Alpha', 'Security', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 28), 'USER', true),
    ('Beth Smith', 'beth.smith@example.com', 'pass', 'UI/UX Developer', 'Beta', 'Design', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 29), 'USER', true),
    ('Carl Brown', 'carl.brown@o2.pl', 'pass', 'DevOps Specialist', 'Gamma', 'IT', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 30), 'USER', true),
    ('Diana Wilson', 'diana.wilson@gmail.com', 'pass', 'Business Systems Analyst', 'Delta', 'Business Analysis', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 31), 'USER', true),
    ('Elijah Miller', 'elijah.miller@example.com', 'pass', 'Network Administrator', 'Alpha', 'IT Support', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 32), 'USER', true),
    ('Fiona Parker', 'fiona.parker@o2.pl', 'pass', 'Technical Writer', 'Beta', 'Documentation', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 33), 'USER', true),
    ('George Turner', 'george.turner@gmail.com', 'pass', 'UX Researcher', 'Gamma', 'Design', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 34), 'USER', true),
    ('Holly Harris', 'holly.harris@example.com', 'pass', 'Business Intelligence Analyst', 'Delta', 'Business Analysis', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 35), 'USER', true),
    ('Ian Evans', 'ian.evans@o2.pl', 'pass', 'Security Analyst', 'Alpha', 'Security', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 36), 'USER', true),
    ('Jasmine Johnson', 'jasmine.johnson@gmail.com', 'pass', 'Product Owner', 'Beta', 'Product Management', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 37), 'USER', true),
    ('Kyle Smith', 'kyle.smith@example.com', 'pass', 'Data Engineer', 'Gamma', 'Analytics', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 38), 'USER', true),
    ('Lila Brown', 'lila.brown@o2.pl', 'pass', 'Software Tester', 'Delta', 'Quality Assurance', (SELECT id FROM inserted_addresses LIMIT 1 OFFSET 39), 'USER', true);