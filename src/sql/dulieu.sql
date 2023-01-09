INSERT INTO `casemd6`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `casemd6`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_USER');
INSERT INTO `casemd6`.`roles` (`id`, `name`) VALUES ('3', 'ROLE_SHOP');


INSERT INTO `casemd6`.`account` (`address`, `birthday`, `date`, `email`, `gender`, `img`, `name`, `password`, `phone_number`, `status`, `username`,`roles_id`) VALUES ('bắc giang', '2012-12-15', '2022-12-12', 'manh12@gmail.com', 'nam', 'https://i.pinimg.com/originals/ce/92/38/ce9238b46ae799e0fba7501a1515c41c.png', 'manh', '1234', '0357123456', '1', 'manh12','1');
INSERT INTO `casemd6`.`account` (`address`, `birthday`, `date`, `email`, `gender`, `img`, `name`, `password`, `phone_number`, `status`, `username`,`roles_id`) VALUES ('hà nội', '2012-12-15', '2022-12-12', 'manh12@gmail.com1', 'nam', 'https://luv.vn/wp-content/uploads/2021/09/hinh-anh-gai-xinh-trung-quoc-95.jpg', 'linh', '1234', '0357123457', '1', 'linh12','1');
INSERT INTO `casemd6`.`account` (`address`, `birthday`, `date`, `email`, `gender`, `img`, `name`, `password`, `phone_number`, `status`, `username`,`roles_id`) VALUES ('thái nguyên', '2012-12-15', '2022-12-12', 'manh12@gmail.com2', 'nam', 'https://haycafe.vn/wp-content/uploads/2022/02/Anh-gai-xinh-co-trang-Trung-Quoc.jpg', 'sơn', '1234', '0357123458', '1', 'son12','1');
INSERT INTO `casemd6`.`account` (`address`, `birthday`, `date`, `email`, `gender`, `img`, `name`, `password`, `phone_number`, `status`, `username`,`roles_id`) VALUES ('nha trang', '2012-12-15', '2022-12-12', 'manh12@gmail.com3', 'nam', 'https://anhdep123.com/wp-content/uploads/2021/02/hinh-anh-gai-dep-trung-quoc.jpeg', 'an', '1234', '0357123459', '1', 'an12','2');
INSERT INTO `casemd6`.`account` (`address`, `birthday`, `date`, `email`, `gender`, `img`, `name`, `password`, `phone_number`, `status`, `username`,`roles_id`) VALUES ('phú thọ', '2012-12-15', '2022-12-12', 'manh12@gmail.com4', 'nam', 'https://thuthuatnhanh.com/wp-content/uploads/2019/07/hinh-hot-girl-trung-quoc-trang-phuc-kiem-hiep.jpg', 'vân', '1234', '0357123410', '1', 'van12','2');
INSERT INTO `casemd6`.`account` (`id`, `address`, `birthday`, `date`, `email`, `gender`, `img`, `name`, `password`, `phone_number`, `status`, `username`, `roles_id`) VALUES ('6', 'trung q', '2012-12-12', '2012-12-12', 'abc', 'nam', 'https://allimages.sgp1.digitaloceanspaces.com/photographercomvn/2020/08/1598022223_173_Anh-gai-xinh-Nhat-ban-Trong-sang-thong-minh-%E2%80%93.jpg', 'sang', '1234', '0366145157', '1', 'sangpk', '3');


-- category
INSERT INTO `casemd6`.`category` (`id`, `name`) VALUES ('1', 'Thời trang');
INSERT INTO `casemd6`.`category` (`id`, `name`) VALUES ('2', 'Đồ điện tử');
INSERT INTO `casemd6`.`category` (`id`, `name`) VALUES ('3', 'Đồ gia dụng');
INSERT INTO `casemd6`.`category` (`id`, `name`) VALUES ('4', 'Mặt hàng khác');

-- trademark
INSERT INTO `casemd6`.`trademark` (`id`, `name`, `category_id`) VALUES ('4', 'OK', '3');
INSERT INTO `casemd6`.`trademark` (`id`, `name`, `category_id`) VALUES ('1', 'NIKE', '1');
INSERT INTO `casemd6`.`trademark` (`id`, `name`, `category_id`) VALUES ('2', 'GUCCI', '1');
INSERT INTO `casemd6`.`trademark` (`id`, `name`, `category_id`) VALUES ('3', 'VIP', '2');

-- shopaddress
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('An Giang');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bà Rịa-Vũng Tàu');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bắc Giang');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bắc Kạn');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bạc Liêu');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bắc Ninh');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bến Tre');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bình Định');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bình Dương');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bình Phước');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Bình Thuận');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Cà Mau');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Cần Thơ');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Cao Bằng');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Đà Nẵng');
INSERT INTO `casemd6`.`shop_address` (`name`) VALUES ('Đắk Lắk');

-- shop
INSERT INTO `casemd6`.`shop` ( `img`, `name`, `status`, `account_id`,`shop_address_id`) VALUES ( 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH1Ht9PM0Un5K1l4PGXm17jDgEbF9fOVe9ew&usqp=CAU', 'shopmanhmanh', '1', '1','3');
INSERT INTO `casemd6`.`shop` ( `img`, `name`, `status`, `account_id`,`shop_address_id`) VALUES ( 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH1Ht9PM0Un5K1l4PGXm17jDgEbF9fOVe9ew&usqp=CAU', 'shoplinlinh', '1', '6','1');

-- product
INSERT INTO `casemd6`.`product` (`id`, `amount`, `detail`, `name`, `price`, `category_id`, `shop_id`) VALUES ('1', '3', 'sp hay', 'áo gucci rep 1:1', '129000', '1', '1');
INSERT INTO `casemd6`.`product` (`amount`, `detail`, `name`, `price`, `category_id`, `shop_id`) VALUES ('2', 'vip', 'sịp lọt khe', '50000', '1', '1');
INSERT INTO `casemd6`.`product` (`amount`, `detail`, `name`, `price`, `category_id`, `shop_id`) VALUES ('3', 'ok', 'B41', '5000000', '4', '2');
INSERT INTO `casemd6`.`product` (`amount`, `detail`, `name`, `price`, `category_id`, `shop_id`) VALUES ('4', 'tạm được', 'Áo nike', '26000', '1', '1');
INSERT INTO `casemd6`.`product` (`amount`, `detail`, `name`, `price`, `category_id`, `shop_id`) VALUES ('2', 'k có mô tả', 'Máy bay chiến đấu', '1000000', '4', '2');
INSERT INTO `casemd6`.`product` (`amount`, `detail`, `name`, `price`, `category_id`, `shop_id`) VALUES ('5', 'hàng vip', 'Nồi đồng cối đá', '200000', '3', '2');

-- billstatus
INSERT INTO `casemd6`.`bill_status` (`id`, `name`) VALUES ('1', 'Chờ xác nhận');
INSERT INTO `casemd6`.`bill_status` (`id`, `name`) VALUES ('2', 'Đang giao hàng');
INSERT INTO `casemd6`.`bill_status` (`id`, `name`) VALUES ('3', 'Đã nhận được hàng');
INSERT INTO `casemd6`.`bill_status` (`id`, `name`) VALUES ('4', 'Hủy đơn hàng');
INSERT INTO `casemd6`.`bill_status` (`id`, `name`) VALUES ('5', 'Đánh giá');

