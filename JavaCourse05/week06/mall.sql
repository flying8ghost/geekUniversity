-- 用户登录表(customer_login)
CREATE TABLE customer_login(
customer_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '用户ID',
username VARCHAR(20) NOT NULL COMMENT '用户登录名',
password VARCHAR(32) NOT NULL COMMENT 'md5加密的密码',
user_stats TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态0:禁用；1:启用',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_customerid(customer_id)
) ENGINE = innodb COMMENT '用户登录表';

-- 用户信息表(customer_info)
CREATE TABLE customer_info(
customer_info_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
customer_id BIGINT NOT NULL COMMENT 'customer_login表的自增ID',
customer_name VARCHAR(20) NOT NULL COMMENT '用户真实姓名',
mobile_phone BIGINT COMMENT '手机号',
customer_email VARCHAR(50) COMMENT '邮箱',
register_time TIMESTAMP NOT NULL COMMENT '注册时间',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_customerinfid(customer_info_id)
) ENGINE = innodb COMMENT '用户信息表';

-- 用户地址表(customer_addr)
CREATE TABLE customer_addr(
customer_addr_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
customer_id BIGINT NOT NULL COMMENT 'customer_login表的自增ID',
zip SMALLINT NOT NULL COMMENT '邮编',
province SMALLINT NOT NULL COMMENT '地区表中省份的ID',
city SMALLINT NOT NULL COMMENT '地区表中城市的ID',
district SMALLINT NOT NULL COMMENT '地区表中的区ID',
address VARCHAR(200) NOT NULL COMMENT '具体的地址门牌号',
is_default TINYINT NOT NULL COMMENT '是否默认',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_customeraddid(customer_addr_id)
) ENGINE = innodb COMMENT '用户地址表';

-- 品牌信息表（brand_info）
CREATE TABLE brand_info(
brand_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '品牌ID',
brand_name VARCHAR(50) NOT NULL COMMENT '品牌名称',
telephone VARCHAR(50) NOT NULL COMMENT '联系电话',
brand_web VARCHAR(100) COMMENT '品牌网络',
brand_logo VARCHAR(100) COMMENT '品牌logo URL',
brand_desc VARCHAR(150) COMMENT '品牌描述',
brand_status TINYINT NOT NULL DEFAULT 0 COMMENT '品牌状态,0禁用,1启用',
brand_order TINYINT NOT NULL DEFAULT 0 COMMENT '排序',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_brandid (brand_id)
)ENGINE=innodb COMMENT '品牌信息表';

-- 分类信息表(product_category)
CREATE TABLE product_category(
category_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '分类ID',
category_name VARCHAR(10) NOT NULL COMMENT '分类名称',
category_code VARCHAR(10) NOT NULL COMMENT '分类编码',
parent_id BIGINT NOT NULL DEFAULT 0 COMMENT '父分类ID',
category_level TINYINT NOT NULL DEFAULT 1 COMMENT '分类层级',
category_status TINYINT NOT NULL DEFAULT 1 COMMENT '分类状态',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_categoryid(category_id)
)ENGINE=innodb COMMENT '商品分类表';

-- 商品信息表(product_info)
CREATE TABLE product_info(
product_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '商品ID',
product_core CHAR(16) NOT NULL COMMENT '商品编码',
product_name VARCHAR(20) NOT NULL COMMENT '商品名称',
brand_id BIGINT NOT NULL COMMENT '品牌表的ID',
category_id BIGINT NOT NULL COMMENT '分类ID',
price DECIMAL(8,2) NOT NULL COMMENT '商品销售价格',
publish_status TINYINT NOT NULL DEFAULT 0 COMMENT '上下架状态：0下架1上架',
audit_status TINYINT NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核，1已审核',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_productid(product_id)
) ENGINE = innodb COMMENT '商品信息表';

-- 商品图片表(productpicinfo)
CREATE TABLE product_pic_info(
product_pic_id BIGINT AUTO_INCREMENT NOT NULL COMMENT '商品图片ID',
product_id BIGINT NOT NULL COMMENT '商品ID',
pic_desc VARCHAR(50) COMMENT '图片描述',
pic_url VARCHAR(200) NOT NULL COMMENT '图片URL',
is_master TINYINT NOT NULL DEFAULT 0 COMMENT '是否主图：0.非主图1.主图',
pic_order TINYINT NOT NULL DEFAULT 0 COMMENT '图片排序',
pic_status TINYINT NOT NULL DEFAULT 1 COMMENT '图片是否有效：0无效 1有效',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_picid(product_pic_id)
)ENGINE=innodb COMMENT '商品图片信息表';


-- 订单主表（order_master）
CREATE TABLE order_master(
order_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
order_sn BIGINT NOT NULL COMMENT '订单编号 yyyymmddnnnnnnnn',
customer_id BIGINT NOT NULL COMMENT '下单人ID',
shipping_user VARCHAR(10) NOT NULL COMMENT '收货人姓名',
province SMALLINT NOT NULL COMMENT '省',
city SMALLINT NOT NULL COMMENT '市',
district SMALLINT NOT NULL COMMENT '区',
address VARCHAR(100) NOT NULL COMMENT '地址',
payment_method TINYINT NOT NULL COMMENT '支付方式：1余额，2支付宝，3微信',
order_money DECIMAL(8,2) NOT NULL COMMENT '订单金额',
district_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额',
shipping_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '运费金额',
payment_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '支付金额',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
order_status TINYINT NOT NULL DEFAULT 0 COMMENT '订单状态',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_orderid(order_id)
)ENGINE = innodb COMMENT '订单主表';

-- 订单详情表（order_detail）
CREATE TABLE order_detail(
order_detail_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
order_id BIGINT NOT NULL COMMENT '订单表ID',
product_id BIGINT NOT NULL COMMENT '订单商品ID',
product_name VARCHAR(50) NOT NULL COMMENT '商品名称',
product_cnt INT NOT NULL DEFAULT 1 COMMENT '购买商品数量',
product_price DECIMAL(8,2) NOT NULL COMMENT '购买商品单价',
fee_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '优惠分摊金额',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_orderdetailid(order_detail_id)
)ENGINE = innodb COMMENT '订单详情表';

-- 购物车表（order_cart）
CREATE TABLE order_cart(
cart_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
customer_id BIGINT NOT NULL COMMENT '用户ID',
product_id BIGINT NOT NULL COMMENT '商品ID',
product_amount INT NOT NULL COMMENT '加入购物车商品数量',
price DECIMAL(8,2) NOT NULL COMMENT '商品价格',
add_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入购物车时间',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_cartid(cart_id)
) ENGINE = innodb COMMENT '购物车表';

-- 仓库信息表（warehouse_info）
CREATE TABLE warehouse_info(
w_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
warehouse_sn CHAR(5) NOT NULL COMMENT '仓库编码',
warehoust_name VARCHAR(10) NOT NULL COMMENT '仓库名称',
warehouse_phone VARCHAR(20) NOT NULL COMMENT '仓库电话',
contact VARCHAR(10) NOT NULL COMMENT '仓库联系人',
province SMALLINT NOT NULL COMMENT '省',
city SMALLINT NOT NULL COMMENT '市',
distrct SMALLINT NOT NULL COMMENT '区',
address VARCHAR(100) NOT NULL COMMENT '仓库地址',
warehouse_status TINYINT NOT NULL DEFAULT 1 COMMENT '仓库状态：0禁用，1启用',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_wid(w_id)
)ENGINE = innodb COMMENT '仓库信息表';

-- 商品库存表（warehouse_product）
CREATE TABLE warehouse_product(
wp_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
product_id BIGINT NOT NULL COMMENT '商品ID',
w_id BIGINT NOT NULL COMMENT '仓库ID',
current_cnt BIGINT NOT NULL DEFAULT 0 COMMENT '当前商品数量',
lock_cnt BIGINT NOT NULL DEFAULT 0 COMMENT '当前占用数据',
modified_time TIMESTAMP NOT NULL COMMENT '最后修改时间',
PRIMARY KEY pk_wpid(wp_id)
)ENGINE = innodb COMMENT '商品库存表';