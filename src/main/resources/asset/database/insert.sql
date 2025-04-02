-- Chèn dữ liệu vào bảng Author
INSERT INTO `Author` (`name`, `year`, `isActive`) VALUES
('Pamela Hutchinson', 1953, 1),
('Dale Carnegie', 1888, 1),
('Jo Condrill, Bennie Bough', null, 1),
('Darrell Mullis & Judith Orloff', 1910, 1),
('Trác Nhã', 1929, 1),
('John C. Maxwell', 1947, 1),
('Bộ Thông tin và Truyền thông', null, 1),
('Kevin Mitnick', 1963, 1),
('Clifford Stoll', 1950, 1),
('Tony Buổi Sáng', 1978, 1),
('Dương Ngọc Duy', 1990, 1);

-- Chèn dữ liệu vào bảng Category
INSERT INTO `Category` (`name`, `isActive`) VALUES
('Nghệ thuật và Điện ảnh', 1),
('Kỹ năng sống', 1),
('Văn học', 1),
('Kế toán', 1),
('Phát triển bản thân', 1),
('Công nghệ', 1),
('Lịch sử', 1),
('Marketing', 1),
('Toán học', 1);


-- Chèn dữ liệu vào bảng Publisher
INSERT INTO `Publisher` (`name`, `isActive`) VALUES
('Nhà xuất bản Kim Đồng', 1),
('Nhà xuất bản Tổng hợp TPHCM', 1),
('Nhà xuất bản Công Thương', 1),
('Nhà xuất bản Thế Giới', 1),
('Nhà xuất bản Văn học', 1),
('Nhà xuất bản Thông tin và Truyền thông', 1),
('Nhà xuất bản Hồng Đức', 1),
('Nhà xuất bản Trẻ', 1),
('Nhà xuất bản Thanh Niên', 1),
('Nhà xuất bản Phụ Nữ', 1);

-- Chèn dữ liệu vào bảng Book
INSERT INTO `Book` (`name`, `isActive`) VALUES
('30 Giây Điện Ảnh', 1),
('Đắc Nhân Tâm', 1),
('Giao Tiếp Với Bất Kỳ Ai', 1),
('Kế Toán Vỉa Hè', 1),
('Khéo Ăn Nói Sẽ Có Được Thiên Hạ', 1),
('Nâng Tầm Ảnh Hưởng', 1),
('Sách Trắng Công Nghệ Thông Tin Và Truyền Thông', 1),
('Nghệ Thuật Ẩn Mình', 1),
('Gián Điệp Mạng', 1),
('Cafe Cùng Tony', 1),
('Đời Đổi Thay Khi Chúng Ta Thay Đổi', 1);

-- Chèn dữ liệu vào bảng BookAuthor
INSERT INTO `BookAuthor` (`authorID`, `bookID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11);

-- Chèn dữ liệu vào bảng BookCategory
INSERT INTO `BookCategory` (`categoryID`, `bookID`) VALUES
(1, 1),
(2, 2),
(2, 3),
(5, 3),
(4, 4),
(2, 5),
(5, 6),
(6, 7),
(5, 8),
(6, 9),
(2, 10),
(2, 11);

-- Chèn dữ liệu vào bảng VersionOfBook
INSERT INTO `VersionOfBook` (`ISBN`, `bookID`, `img`, `publisherID`, `edition`, `location`, `price`, `quantity`, `available`) VALUES
('9786042282512', 1, '/asset/img/book/30giaydienanh.png', 1, '1st', 'L1-A1', 140000, 50, 50),
('9786043927436', 2, '/asset/img/book/dacnhantam.jpg', 2, '3rd', 'L1-A2', 80000, 30, 25),
('9786044022741', 3, '/asset/img/book/giaotiepbatkyai.png', 3, '1st', 'L1-B2', 50000, 40, 30),
('9786047787616', 4, '/asset/img/book/ketoanviahe.png', 4, '1st', 'L1-B3', 130000, 35, 20),
('9786043629224', 5, '/asset/img/book/kheoankheonoi.png', 5, '2nd', 'L1-C1', 138000, 50, 45),
('9786045538604', 6, '/asset/img/book/nangtamanhhuong.png', 3, '1st', 'L1-C3', 88000, 25, 15),
('9786049525795', 7, '/asset/img/book/sachtrangcntt.png', 6, '3rd', 'L2-A1', 220000, 30, 25),
('9786048706931', 8, '/asset/img/book/nghethuatnminh.jpg', 7, '2nd', 'L2-B1', 130000, 20, 15),
('9786041086374', 9, '/asset/img/book/giandiepmang.jpg', 8, '1st', 'L2-B2', 140000, 45, 35),
('9786040150148', 10, '/asset/img/book/cafecungtony.jpg', 8, '1st', 'L2-C1', 80000, 40, 32),
('9786045605063', 11, '/asset/img/book/doidoithaykhichungtathaydoi.jpg', 2, '1st', 'L2-A2', 95000, 20, 16);

-- Chèn dữ liệu vào bảng Person
INSERT INTO `Person` (`id`, `name`, `tel`, `address`, `schoolYear`, `isActive`) VALUES
('0000000001', 'Nguyễn Văn A', '0909123456', 'Hà Nội', '2023-2024', 1),
('0000000002', 'Trần Thị B', '0912345678', 'TP.HCM', '2022-2023', 1),
('0000000003', 'Lê Văn C', '0923456789', 'Đà Nẵng', '2024-2025', 1),
('0000000004', 'Nguyễn Thị D', '0908765432', 'Huế', '2021-2022', 1),
('0000000005', 'Lê Thị E', '0919876543', 'Đà Lạt', '2020-2021', 1),
('0000000006', 'Phan Văn F', '0921987654', 'Quảng Ninh', '2019-2020', 1),
('0000000007', 'Trần Văn G', '0932198765', 'Nha Trang', '2022-2023', 1),
('1000000001', 'Phạm Thị H', '0943219876', 'Hải Phòng', null, 1),
('1000000002', 'Ngô Văn I', '0954321987', 'Cần Thơ', null, 1),
('1000000003', 'Lê Văn J', '0965432198', 'Biên Hòa', null, 1),
('1000000004', 'Phan Thị Anh Thư', '0901236548', 'Tiền Giang', null, 1),
('1000000005', 'Trần Bảo Trân', '0901244444', 'TP HCM', null, 1),
('1000000006', 'Trần Đức An', '0901228399', 'TP HCM', null, 1),
('1000000007', 'Trần Thị Thủy', '0901446548', 'Quảng Trị', null, 1),
('1000000008', 'Nguyễn Thanh Tiến', '0922236548', 'Bình Dương', null, 1);

-- Chèn dữ liệu vào bảng Role
INSERT INTO `Role` (`id`, `name`) VALUES
('AD', 'Admin'),
('TT', 'Thủ thư'),
('TK', 'Thủ kho'),
('QL', 'Quản lý'),
('GV', 'Giảng viên'),
('SV', 'Sinh viên');

-- Chèn dữ liệu vào bảng Account
INSERT INTO `Account` (`id`, `password`, `positionID`) VALUES
('0000000001', '123456', 'SV'),
('0000000002', '123456', 'SV'),
('0000000003', '123456', 'SV'),
('0000000004', '123456', 'SV'),
('0000000005', '123456', 'SV'),
('0000000006', '123456', 'SV'),
('0000000007', '123456', 'SV'),
('1000000001', '111111', 'GV'),
('1000000002', '111111', 'GV'),
('1000000003', '111111', 'GV'),
('1000000004', '000000', 'TT'),
('1000000005', '222222', 'AD'),
('1000000006', '333333', 'TK'),
('1000000007', '444444', 'QL'),
('1000000008', '444444', 'QL');

-- Chèn dữ liệu vào bảng Borrowing
INSERT INTO `Borrowing` (`readerID`, `borrowStaffID`, `returnStaffID`, `borrowDate`, `dueDate`, `returnDate`)
VALUES 
('0000000001', '1000000004', '1000000004', '2024-10-01', '2024-10-15', NULL),
('0000000003', '1000000004', NULL, '2024-10-03', '2024-10-17', NULL),
('0000000006', '1000000004', '1000000004', '2024-10-05', '2024-10-19', '2024-10-18'),
('0000000007', '1000000004', NULL, '2024-10-06', '2024-10-20', NULL),
('0000000005', '1000000004', '1000000004', '2024-10-01', '2024-10-15', NULL),
('1000000001', '1000000004', NULL, '2024-10-03', '2024-10-17', NULL),
('1000000002', '1000000004', NULL, '2024-10-05', '2024-10-19', NULL),
('1000000003', '1000000004', NULL, '2024-10-06', '2024-10-20', NULL),
('0000000002', '1000000004', '1000000004', '2024-10-07', '2024-10-21', '2024-10-22');

-- Chèn dữ liệu vào bảng BorrowDetail
INSERT INTO `BorrowDetail` (borrowID, ISBN, quantity, description)
VALUES 
(1, '9786042282512', 1, NULL),
(2, '9786043927436', 2, NULL),
(3, '9786044022741', 1, 'rách trang 12'),
(4, '9786047787616', 1, NULL),
(5, '9786043629224', 1, NULL),
(1, '9786045538604', 1, NULL),
(3, '9786049525795', 2, NULL),
(2, '9786048706931', 1, NULL),
(5, '9786041086374', 1, NULL),
(4, '9786040150148', 2, NULL),
(1, '9786045605063', 1, 'không có bìa');


-- Chèn dữ liệu vào bảng Supplier
INSERT INTO `Supplier` (`name`, `address`, `tel`) VALUES
('Công ty Sách A', 'Số 123, Phố Hoàng Diệu, Quận Ba Đình, Hà Nội', '0987654321'),
('Công ty Sách B', 'Số 456, Đường Nguyễn Huệ, Quận 1, TP.HCM', '0976543210'),
('Công ty Sách C', 'Số 789, Đường Bạch Đằng, Quận Hải Châu, Đà Nẵng', '0965432109'),
('Công ty Sách D', 'Số 321, Đường Lê Lợi, Thành phố Huế, Thừa Thiên Huế', '0954321098'),
('Công ty Sách E', 'Số 654, Đường Trần Hưng Đạo, Thành phố Hạ Long, Quảng Ninh', '0943210987'),
('Tập thể lớp DCT1211', 'Số 237, Đường An Dương Vương, Quận 5, TP.HCM', '0932109876'),
('Công ty Sách G', 'Số 98, Đường Lạch Tray, Quận Ngô Quyền, Hải Phòng', '0921098765'),
('Công ty Sách H', 'Số 567, Đường Nguyễn Trãi, Quận Ninh Kiều, Cần Thơ', '0910987654'),
('Tập thể khoa CNTT', 'Số 237, Đường An Dương Vương, Quận 5, TP.HCM', '0909876543'),
('Công ty Sách J', 'Số 101, Đường Phan Đình Phùng, Thành phố Đà Lạt, Lâm Đồng', '0898765432'),
('Công ty Sách T', 'Số 55, Đường Trần Hưng Đạo, Thành phố Mỹ Tho, Tiền Giang', '0898568990');



-- Chèn dữ liệu vào bảng Importing
INSERT INTO `Importing` (`supplierID`, `staffID`, `fee`) VALUES
(1, '1000000006', 500000),
(2, '1000000006', 300000),
(3, '1000000006', 700000),
(4, '1000000006', 450000),
(5, '1000000006', 600000),
(6, '1000000006', 350000),
(7, '1000000006', 800000),
(8, '1000000006', 400000),
(9, '1000000006', 750000),
(10, '1000000006', 550000),
(11, '1000000006', 550000);


-- Chèn dữ liệu vào bảng ImportDetail
INSERT INTO `ImportDetail` (importID, ISBN, quantity)
VALUES 
(1, '9786042282512', 50),
(2, '9786043927436', 30),
(3, '9786044022741', 40),
(4, '9786047787616', 35),
(5, '9786043629224', 50),
(6, '9786045538604', 25),
(7, '9786049525795', 30),
(8, '9786048706931', 20),
(9, '9786041086374', 45),
(10, '9786040150148', 40),
(11, '9786045605063', 20);