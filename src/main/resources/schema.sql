use storagedb;
CREATE TABLE IF NOT EXISTS `storage` (
                           `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           `file_name` varchar(255) NOT NULL,
                           `type` varchar(255) NOT NULL,
                           `content` mediumblob,
                           `created_at` datetime NOT NULL,
                           `created_by` varchar(255) NOT NULL,
                           `updated_at` datetime DEFAULT NULL,
                           `updated_by` varchar(255) DEFAULT NULL);