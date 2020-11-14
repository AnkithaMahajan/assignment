DROP TABLE IF EXISTS CATEGORY_DATA;

CREATE TABLE CATEGORY_DATA (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  PARENT_ID INT NOT NULL,
  NAME VARCHAR(250) NOT NULL,
  COLOUR VARCHAR(250) DEFAULT NULL
);

INSERT INTO CATEGORY_DATA (PARENT_ID, NAME, COLOUR) VALUES
  (0, 'Warrior', 'Red'),
  (0, 'Wizard', 'Green'),
  (0, 'Priest', 'White'),
  (0, 'Rogue', 'Yellow'),
  (1, 'Fighter', 'Blue'),
  (1, 'Paladin', 'Lightblue'),
  (1, 'Ranger', 'Lightgreen'),
  (2, 'Mage', 'Grey'),
  (2, 'Specialist wizard', 'Lightgrey'),
  (3, 'Cleric', 'Red'),
  (3, 'Druid', 'Green'),
  (3, 'Priest of specific mythos', 'White'),
  (4, 'Thief', 'Yellow'),
  (4, 'Bard', 'Blue'),
  (13, 'Assassin', 'Lightblue');


