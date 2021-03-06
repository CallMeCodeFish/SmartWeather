create table if not exists recommendations
(
    reco_id int          not null,
    type    varchar(50)  not null,
    text    varchar(256) not null,
    constraint recommendations_pk
        primary key (reco_id)
);

insert ignore into recommendations
VALUES (101, 'Clothing', 'Wear coat and bring umbrella'),
       (201, 'Activity', 'It''s not recommended to go out in a thunderstorm'),
       (301, 'Plant', 'Take your plants indoors for thunderstorm'),
       (102, 'Clothing', 'Bring umbrella because it''s going to rain.'),
       (202, 'Activity', 'Indoor activities is recommended on a rainy day.'),
       (302, 'Plant', 'Watch out for the plants in case they got too much water.'),
       (103, 'Clothing', 'Wear snow boots and snow coat.'),
       (203, 'Activity', 'Play with snow and build a snowman or stay indoors to keep warm.'),
       (303, 'Plant', 'Take your plants indoors for the snow.'),
       (104, 'Clothing', 'Wear yellow or red clothes to remind passing vehicle.'),
       (204, 'Activity',
        'Outdoor exercises are not recommended, which will breathe in too much harmful substances. It''s bettter to do indoor exercises like yoga, swimming or go to the gym. '),
       (304, 'Plant',
        'Haze affects the exposure of sunlight and thus the photosynthesis of plants. Apply proper fertilizer and keep plants warm to provide sufficient nutrients for the plants'),
       (105, 'Clothing', 'Stay at home! '),
       (205, 'Activity', 'Do indoor exercises like yoga, or use running machine'),
       (305, 'Plant', 'Keep plants indoors and fertilized.'),
       (106, 'Clothing', 'Wear T-shirt, shorts and sunglasses.'),
       (206, 'Activity', 'Hiking, camping, fishing, jogging, boating. Take kids to theme parks.'),
       (306, 'Plant', 'Plants need sunlight. Put them under the sun and dont forget to water them.'),
       (107, 'Clothing', 'Wear a coat, trousers. Bring an umbrella if going out. '),
       (207, 'Activity', 'Watch a movie. Go to gym'),
       (307, 'Plant', 'Keep plants watered.');

create table if not exists weather_codes
(
    code int auto_increment,
    text varchar(16) not null,
    constraint weather_codes_pk
        primary key (code)
);

insert ignore into weather_codes (code, text)
VALUES (200, 'Thunderstorm'),
       (201, 'Thunderstorm'),
       (202, 'Thunderstorm'),
       (210, 'Thunderstorm'),
       (211, 'Thunderstorm'),
       (212, 'Thunderstorm'),
       (221, 'Thunderstorm'),
       (230, 'Thunderstorm'),
       (231, 'Thunderstorm'),
       (232, 'Thunderstorm'),
       (301, 'Drizzle'),
       (302, 'Drizzle'),
       (310, 'Drizzle'),
       (311, 'Drizzle'),
       (312, 'Drizzle'),
       (313, 'Drizzle'),
       (314, 'Drizzle'),
       (321, 'Drizzle'),
       (500, 'Rain'),
       (501, 'Rain'),
       (502, 'Rain'),
       (503, 'Rain'),
       (504, 'Rain'),
       (511, 'Rain'),
       (520, 'Rain'),
       (521, 'Rain'),
       (522, 'Rain'),
       (531, 'Rain'),
       (600, 'Snow'),
       (601, 'Snow'),
       (602, 'Snow'),
       (611, 'Snow'),
       (612, 'Snow'),
       (613, 'Snow'),
       (615, 'Snow'),
       (616, 'Snow'),
       (620, 'Snow'),
       (621, 'Snow'),
       (622, 'Snow'),
       (701, 'Mist'),
       (711, 'Smoke'),
       (721, 'Haze'),
       (731, 'Dust'),
       (741, 'Fog'),
       (751, 'Sand'),
       (761, 'Dust'),
       (762, 'Ash'),
       (771, 'Squall'),
       (781, 'Tornado'),
       (800, 'Sunny'),
       (801, 'Clouds'),
       (802, 'Clouds'),
       (803, 'Clouds'),
       (804, 'Clouds');

create table if not exists weather_reco_mappings
(
    code    int not null,
    reco_id int not null,
    constraint weather_reco_mappings_pk
        primary key (code, reco_id),
    constraint weather_code_fk
        foreign key (code) references weather_codes (code),
    constraint reco_fk
        foreign key (reco_id) references recommendations (reco_id)
);

insert ignore into weather_reco_mappings
VALUES (200, 101),
       (200, 201),
       (200, 301),
       (201, 101),
       (201, 201),
       (201, 301),
       (202, 101),
       (202, 201),
       (202, 301),
       (210, 101),
       (210, 201),
       (210, 301),
       (211, 101),
       (211, 201),
       (211, 301),
       (212, 101),
       (212, 201),
       (212, 301),
       (221, 101),
       (221, 201),
       (221, 301),
       (230, 101),
       (230, 201),
       (230, 301),
       (231, 101),
       (231, 201),
       (231, 301),
       (232, 101),
       (232, 201),
       (232, 301),

       (500, 102),
       (500, 202),
       (500, 302),
       (501, 102),
       (501, 202),
       (501, 302),
       (502, 102),
       (505, 202),
       (502, 302),
       (503, 102),
       (503, 202),
       (503, 302),
       (504, 102),
       (504, 202),
       (504, 302),
       (511, 102),
       (511, 202),
       (511, 302),
       (520, 102),
       (520, 202),
       (520, 302),
       (521, 102),
       (521, 202),
       (521, 302),
       (522, 102),
       (522, 202),
       (522, 302),
       (531, 102),
       (531, 202),
       (531, 302),

       (600, 103),
       (600, 203),
       (600, 303),
       (601, 103),
       (601, 203),
       (601, 303),
       (602, 103),
       (602, 203),
       (602, 303),
       (611, 103),
       (611, 203),
       (611, 303),
       (612, 103),
       (612, 203),
       (612, 303),
       (613, 103),
       (613, 203),
       (613, 303),
       (615, 103),
       (615, 203),
       (615, 303),
       (616, 103),
       (616, 203),
       (616, 303),
       (620, 103),
       (620, 203),
       (620, 303),
       (621, 103),
       (621, 203),
       (621, 303),
       (622, 103),
       (622, 203),
       (622, 303),

       (701, 104),
       (701, 204),
       (701, 304),
       (711, 104),
       (711, 204),
       (711, 304),
       (721, 104),
       (721, 204),
       (721, 304),
       (731, 104),
       (731, 204),
       (731, 304),
       (741, 104),
       (741, 204),
       (741, 304),
       (751, 104),
       (751, 204),
       (751, 304),
       (761, 104),
       (761, 204),
       (761, 304),
       (762, 104),
       (762, 204),
       (762, 304),

       (771, 105),
       (771, 205),
       (771, 305),
       (781, 105),
       (781, 205),
       (781, 305),

       (800, 106),
       (800, 206),
       (800, 306),

       (801, 107),
       (801, 207),
       (801, 307),
       (802, 107),
       (802, 207),
       (802, 307),
       (803, 107),
       (803, 207),
       (803, 307),
       (804, 107),
       (804, 207),
       (804, 307);