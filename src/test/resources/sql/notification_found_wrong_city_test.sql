INSERT INTO users
(id, name, city_name, allow_notifications, created_at, updated_at)
VALUES
(1, 'Matheus-Test-Notification', 'wrong_city', true, '2025-02-06 23:31:50.046', '2025-02-06 23:31:50.046');


INSERT INTO notifications
(id, status, city_name, notification_date, created_at, updated_at, error_message)
VALUES
(1, 'PENDING', 'wrong_city', '2025-02-05 23:31:50.044', '2025-02-06 23:31:50.044', '2025-02-06 23:32:20.538', NULL);