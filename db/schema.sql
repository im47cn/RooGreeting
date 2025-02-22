CREATE TABLE IF NOT EXISTS greetings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    greeting_time DATETIME NOT NULL,
    greeting_message VARCHAR(255) NOT NULL,
    greeting_date VARCHAR(10) NOT NULL,
    INDEX idx_greeting_date (greeting_date),
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;