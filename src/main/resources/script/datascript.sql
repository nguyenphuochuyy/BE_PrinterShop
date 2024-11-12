use qlmayin 
INSERT INTO Category (Description, ImgUri, CategoryName) VALUES
('Máy in có tốc độ in cao', 'img/printers/high_speed.jpg', 'Máy in tốc độ cao'),
('Máy in nhỏ gọn, tiết kiệm không gian', 'img/printers/compact.jpg', 'Máy in nhỏ gọn'),
('Máy in hỗ trợ kết nối không dây', 'img/printers/wireless.jpg', 'Máy in không dây'),
('Máy in giá rẻ, phù hợp túi tiền', 'img/printers/budget.jpg', 'Máy in giá rẻ'),
('Máy in màu có độ phân giải cao', 'img/printers/color.jpg', 'Máy in màu');

INSERT INTO Product (Description, Img, InStock, ProductName, Price, Ram, SizePage, CategoryId) VALUES
-- Máy in tốc độ cao
(N'Máy in tốc độ cao, phù hợp cho văn phòng bận rộn', N'img/printers/high_speed_1.jpg', 50, N'Máy in tốc độ A', 3000000, 512, N'A4', 1),
(N'Máy in laser với tốc độ in nhanh và hiệu quả', N'img/printers/high_speed_2.jpg', 30, N'Máy in tốc độ B', 4500000, 1024, N'A4', 1),
(N'Máy in đa chức năng với tốc độ in cao', N'img/printers/high_speed_3.jpg', 20, N'Máy in tốc độ C', 5500000, 1024, N'A3', 1),
(N'Máy in đen trắng có tốc độ in nhanh', N'img/printers/high_speed_4.jpg', 40, N'Máy in tốc độ D', 3500000, 512, N'A4', 1),

-- Máy in nhỏ gọn
(N'Máy in mini phù hợp với không gian nhỏ', N'img/printers/compact_1.jpg', 15, N'Máy in nhỏ gọn A', 2500000, 256, N'A4', 2),
(N'Máy in văn phòng nhỏ gọn, tiện lợi', N'img/printers/compact_2.jpg', 25, N'Máy in nhỏ gọn B', 2800000, 256, N'A4', 2),
(N'Máy in cá nhân, tiết kiệm diện tích', N'img/printers/compact_3.jpg', 18, N'Máy in nhỏ gọn C', 3200000, 512, N'A4', 2),
(N'Máy in dành cho gia đình với thiết kế nhỏ gọn', N'img/printers/compact_4.jpg', 20, N'Máy in nhỏ gọn D', 3000000, 256, N'A5', 2),

-- Máy in không dây
(N'Máy in hỗ trợ kết nối không dây tiện lợi', N'img/printers/wireless_1.jpg', 10, N'Máy in không dây A', 4000000, 1024, N'A4', 3),
(N'Máy in không dây, hỗ trợ in từ điện thoại', N'img/printers/wireless_2.jpg', 15, N'Máy in không dây B', 4500000, 1024, N'A4', 3),
(N'Máy in không dây với chất lượng cao', N'img/printers/wireless_3.jpg', 12, N'Máy in không dây C', 5000000, 2048, N'A3', 3),
(N'Máy in không dây dành cho văn phòng', N'img/printers/wireless_4.jpg', 8, N'Máy in không dây D', 5500000, 2048, N'A3', 3),

-- Máy in giá rẻ
(N'Máy in giá rẻ cho nhu cầu in ấn cơ bản', N'img/printers/budget_1.jpg', 25, N'Máy in giá rẻ A', 1500000, 128, N'A4', 4),
(N'Máy in giá rẻ, tiết kiệm chi phí', N'img/printers/budget_2.jpg', 30, N'Máy in giá rẻ B', 1800000, 256, N'A4', 4),
(N'Máy in văn phòng giá rẻ, phù hợp ngân sách', N'img/printers/budget_3.jpg', 20, N'Máy in giá rẻ C', 2000000, 512, N'A4', 4),
(N'Máy in đơn giản, giá thành hợp lý', N'img/printers/budget_4.jpg', 35, N'Máy in giá rẻ D', 1700000, 256, N'A5', 4);

INSERT INTO Coupon (CouponCode, TypeCoupon, ValueCoupon, EndDate, MinPurchase, StartDate, UsageCount, UsageLimit) VALUES
('DISCOUNT10', N'Giảm giá phần trăm', 10, '2024-12-31', 500000.0, '2024-01-01', 0, 100),
('NEWYEAR20', N'Giảm giá phần trăm', 20, '2025-01-10', 1000000.0, '2024-12-25', 0, 50),
('SPRING15', N'Giảm giá phần trăm', 15, '2024-03-31', 700000.0, '2024-02-01', 0, 75),
('FREESHIP', N'Miễn phí vận chuyển', 0, '2024-12-31', 200000.0, '2024-01-01', 0, 150),
('SUMMER25', N'Giảm giá phần trăm', 25, '2024-08-31', 1200000.0, '2024-06-01', 0, 100),
('BACK2SCHOOL', N'Giảm giá phần trăm', 20, '2024-09-15', 800000.0, '2024-08-15', 0, 60),
('AUTUMN30', N'Giảm giá phần trăm', 30, '2024-11-30', 1500000.0, '2024-09-01', 0, 40),
('WINTER10', N'Giảm giá cố định', 100000, '2024-12-31', 500000.0, '2024-11-01', 0, 90),
('WELCOME5', N'Giảm giá cố định', 50000, '2025-01-31', 300000.0, '2024-12-01', 0, 200),
('BIGSALE50', N'Giảm giá phần trăm', 50, '2024-12-31', 2500000.0, '2024-11-25', 0, 30);
