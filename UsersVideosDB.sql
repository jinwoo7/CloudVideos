# -----------------------------------------------
# SQL script to create the UsersVideosDB database
# tables and populate the PublicVideo table.
# Created by Osman Balci
# -----------------------------------------------

/*
Tables to be dropped must be listed in a logical order based on dependency.
UserVideo and UserPhoto depend on User. Therefore, they must be dropped before User.
*/
DROP TABLE IF EXISTS PublicVideo, UserVideo, UserPhoto, User;

/* The User table contains attributes of interest of a User. */
CREATE TABLE User
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR (32) NOT NULL,
    password VARCHAR (32) NOT NULL,
    first_name VARCHAR (32) NOT NULL,
    middle_name VARCHAR (32),
    last_name VARCHAR (32) NOT NULL,
    address1 VARCHAR (128) NOT NULL,
    address2 VARCHAR (128),
    city VARCHAR (64) NOT NULL,
    state VARCHAR (2) NOT NULL,
    zipcode VARCHAR (10) NOT NULL, /* e.g., 24060-1804 */
    security_question INT NOT NULL, /* Refers to the number of the selected security question */
    security_answer VARCHAR (128) NOT NULL,
    email VARCHAR (128) NOT NULL,      
    PRIMARY KEY (id)
);

/* The UserPhoto table contains attributes of interest of a user's profile photo. */
CREATE TABLE UserPhoto
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    extension ENUM('jpeg', 'jpg', 'png', 'gif') NOT NULL,
    user_id INT UNSIGNED,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/* The UserVideo table contains attributes of interest of a user's favorite video. */
CREATE TABLE UserVideo
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id INT UNSIGNED,
    title VARCHAR (256) NOT NULL,
    description VARCHAR (512) NOT NULL,
    youtube_video_id VARCHAR (32) NOT NULL,
    duration VARCHAR (8) NOT NULL,
    date_published DATE NOT NULL,
    category VARCHAR (32) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/* The PublicVideo table contains attributes of interest of public's favorite video. */
CREATE TABLE PublicVideo
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR (256) NOT NULL,
    description VARCHAR (512) NOT NULL,
    youtube_video_id VARCHAR (32) NOT NULL,
    duration VARCHAR (8) NOT NULL,
    date_published DATE NOT NULL,
    category VARCHAR (32) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO PublicVideo (title, description, youtube_video_id, duration, date_published, category) VALUES
('Top 5 Future Technology', 'This video shows the top 5 future technological inventions and creations which are expected to be available in between 2019 to 2050', 'Ed92step_K8', '4:35', '2016-05-05', 'Science & Technology'),
('Shuffle Dance', 'Best Music Mix 2017 - Shuffle Dance Music Video HD', 'myYZ2I5DMe4', '1:07:00', '2017-01-11', 'Music'),
('Virginia Tech Football', 'A 2016 Virginia Tech Football Trailer', 'pet7vk99Iko', '3:00', '2016-08-26', 'Sports'),
('Super Mario Odyssey', 'Super Mario Odyssey sees Mario leave the Mushroom Kingdom to go on a new sandbox-style journey!', '5kcdRBHM7kM', '2:42', '2017-01-12', 'Gaming'),
('Animal Animation', 'Funny animal animation video short film', 'KsG329WLiOM', '4:18', '2014-12-14', 'Film & Animation'),
('Cars of the Future', 'Car Technology of the Future. One perspective of what automobiles of the future might be capable of.', 'lZZGpMjJr4A', '1:29:49', '2014-03-29', 'Cars & Vehicles'),
('Funny Cat Compilation', 'Cats are simply the funniest and most hilarious pets, they make us laugh all the time!', '5dsGWM5XGdg', '10:05', '2016-12-24', 'Pets & Animals'),
('Jeff Dunham', 'Meet Achmed the Dead Terrorist - Spark of Insanity', 'GBvfiCdk-jc', '4:53', '2015-01-27', 'Entertainment'),
('How to Shuffle Dance', 'A video teaching how to shuffle dance', 'RECaepj8LkU', '3:05', '2015-08-26', 'How-to & Style'),
('White House Press Conference', 'Sean Spicer (Melissa McCarthy) and Jeff Sessions (Kate McKinnon) take questions from the press on Saturday Night Live', 'fbhz3XcNzGU', '8:19', '2017-02-12', 'Comedy'),
('Trump Crossed the Line', 'Fox News anchor Chris Wallace warns viewers: Trump crossed the line in latest attack on media', 'g7LQ3pkzUJs', '5:33', '2017-02-19', 'News & Politics'),
('Climate Change', 'Why is Al Gore optimistic about climate change? Gore asks three powerful questions about the man-made forces threatening to destroy our planet.', 'gVfgkFaswn4', '25:20', '2016-03-14', 'Science & Technology'),
('Cavemen', 'Cavemen Funny Animated 3D Short Film', 'aUN6RPMIoeo', '3:30', '2014-03-26', 'Film & Animation'),
('Vacation in Hawaii', "Hawaii's Big Island Vacation Travel Guide by Expedia", 'XhHwGolzQmg', '4:58', '2013-10-29', 'Travel & Events'),
('Love', 'Listen to song Love by Lana Del Rey', '3-NTv0CdFCk', '4:54', '2017-02-20', 'Music'),
("Trump People's Court", 'President (Alec Baldwin) versus the Ninth Circuit Court judges on Saturday Night Live', 'dLYfwprjtog', '4:53', '2017-02-12', 'Comedy'),
('The Story of Max Huber', 'Pursuit of a man for something better! Created for Creme De La Mer by the talented team over at Psyop.', 'RqajcuJddmU', '1:57', '2017-02-21', 'Film & Animation'),
('DARPA Technology', 'Most Feared DARPA Technology for U.S. Military', 'vICFah-aDFY', '45:49', '2016-05-12', 'Science & Technology'),
('CandyBot Educational Game', 'CandyBot is created to teach students about fractions and functions by the Learning Transformation Research Group at Virginia Tech.', 'XkGl8i_GDfo', '3:18', '2015-04-06', 'Education'),
('NBA All-Star Celebrity Game', '2017 NBA All-Star Celebrity Game full game highlights from New Orleans Louisiana', '64KjHUwJF1w', '9:33', '2017-02-17', 'Sports'),
('Most Amazing Beaches', 'Top 25 amazing beaches to add to your bucket list. Be sure to visit some of these amazing and beautiful beaches!', '0BugQWcRov0', '9:31', '2016-03-11', 'Travel & Events'),
("America's Got Talent", "Tape Face Auditions and Performances - America's Got Talent 2016 Finalist", 'JdQLUIsqxvc', '17:50', '2016-09-25', 'Entertainment'),
('Spider Man', 'The Amazing Spider Man 2 Video Game', 'o9kr9ZhydK0', '16:36', '2014-05-06', 'Gaming'),
('Top 10 New Cars', 'Review of Top 10 New Cars Coming in 2017', 'rP5_FfC0qac', '7:56', '2016-08-05', 'Cars & Vehicles'),
('Robert Irwin and Jimmy Fallon', 'Robert Irwin, 13-year-old son of Crocodile Hunter Steve Irwin, shows Jimmy Fallon some animals.', 'BrVfuDx4ET8', '8:34', '2017-02-17', 'Entertainment'),
('Oval Office Cold Open', 'President Donald Trump (Alec Baldwin) calls Australian Prime Minister, Mexican President, and German Chancellor Angela Merkel.', 'pZOF9q5fzfs', '6:32', '2017-02-05', 'Comedy'),
('How to Tie a Tie', 'A step by step tutorial on how to tie a tie', 'xAg7z6u4NE8', '5:40', '2010-07-28', 'How-to & Style'),
('Fareed Zakaria', "Fareed Zakaria's take on the Trump Presidency", 'CKYRf1EJoe0', '5:01', '2017-02-19', 'News & Politics'),
('Jimmy Fallon', 'Kevin Hart and Jimmy Fallon take turns trying to guess what the audience chose when given random Would You Rather scenarios', 'QqivtpiYFDU', '7:03', '2016-10-11', 'Entertainment'),
('Car Doors', 'Top 12 Insane Car Doors You Must See', 'rZcRcub-sQc', '12:37', '2016-12-17', 'Cars & Vehicles');
