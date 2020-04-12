create table if not exists user
(
    id int auto_increment,
    name varchar(50) null,
    account_type int null,
    account_id varchar(50) null,
    avatar_url varchar(100) null,
    token char(36) null,
    created_at bigint null,
    updated_at bigint null,
    constraint user_pk
        primary key (id)
);

create table if not exists recommendation
(
    id int auto_increment,
    type varchar(50) not null,
    text varchar(256) not null,
    weather varchar(15) not null,
    constraint recommendation_pk
        primary key (id)
);

insert into recommendation (ID, type, text, weather) VALUES (NULL, "Clothing", "Wear coat and bring umbrella", "Thunderstorm");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Activity", "It''s not recommended to go out in a thunderstorm.", "Thunderstorm");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Plant", "Take your plants indoors for thunderstorm.", "Thunderstorm");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Clothing", "Bring umbrella because it''s going to rain.", "Rain");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Activity", "Indoor activities is recommended on a rainy day.", "Rain");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Plant", "Watch out for the plants in case they got too much water.", "Rain");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Clothing", "Wear snow boots and snow coat.", "Snow");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Activity", "Play with snow and build a snowman or stay indoors to keep warm.", "Snow");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Plant", "Take your plants indoors for the snow.", "Snow");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Clothing", "Wear yellow or red clothes to remind passing vehicle.", "Haze");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Activity", "Outdoor exercises are not recommended, which will breathe in too much harmful substances. It''s bettter to do indoor exercises like yoga, swimming or go to the gym. ", "Haze");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Plant", "Haze affects the exposure of sunlight and thus the photosynthesis of plants. Apply proper fertilizer and keep plants warm to provide sufficient nutrients for the plants", "Haze");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Clothing", "Stay at home! ", "Squall");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Activity", " Do indoor exercises like yoga, or use running machine", "Squall");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Plant", "Keep plants indoors and fertilized.", "Squall");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Clothing", "Ware T-shirt, shorts and Sunglasses.", "Sunny");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Activity", "Hiking, camping, fishing, jogging, boating. Take kids to theme parks.", "Sunny");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Plant", "Plants need sunlight. Put them under the sun and dont forget to water them.", "Sunny");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Clothing", "Ware a coat, trousers. Bring an unbrella if going out. ", "Cloudy");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Activity", "Watch a movie. Go to gym", "Cloudy");
insert into recommendation (ID, type, text, weather) VALUES (NULL, "Plant", "Keep plants watered.", "Cloudy");
