#  Nguyễn Đức Tài - 20077681 - DHKTPM16A
### Kiến Trúc Phần Mềm Tuần 5

**Nội dung thực hành**


[![N|Solid](https://i.imgur.com/pRbcOmw.jpeg)]()

Bài tập giúp ôn tập lại các kiến thức về JPA , Hibernate ,MariaDb ThymeLaf ,LooBook,,, Sử dụng hàng đợi ActiveMQ , Sử dụng Java mail 

- ✨Magic ✨

## Kết Quả

- Khi config chương trình và chạy ta sẽ chạy trên port http://localhost:8090/ và hiển thị kết quả" :
-[![N|Solid](https://i.imgur.com/ryxH3s5.png)]()
- Ta thử thêm 1 sản phẩm ví dụ. Tên Sản Phẩm : Iphone 8 Plus,	Giá : 1000	Số Lượng Trong Kho : 100 , sau khi thêm vào ta thấy dữ liệu đã có trong bảng , ta kiểm tra trên cỡ sỡ dữ liệu mariadb ở xampp
[![N|Solid](https://i.imgur.com/qkVIhMU.png)]()
[![N|Solid](https://i.imgur.com/ZYi7Tj9.png)]()
- Ta qua mục Đặt Hàng Online , ở đây người dùng sẽ nhập mua hàng từ sản phẩm có sẵn 
[![N|Solid](https://i.imgur.com/jKCUfx4.png)]()
- Cần bật giao diện server của Active MQ , Nếu chưa cài thì cài tại đây [ Active MQ](https://activemq.apache.org/components/classic/download/) , hiện bài tập này đang chạy trên queue là 'dat_hang' sử dụng mô hình PublicsherConSumer hiện tại đã có 16 message được đưa vào thành công , và không có message nào trong hàng đợi
![N|Solid](https://i.imgur.com/Uc9QdKU.png)]()
- Ta tiến hành thêm 1 đơn đặt hàng Online với tên KH : Nguyễn Tuấn Anh , SP đặt là Iphone 15 , Số lượng đặt là 200 , email là taicutm@gmail.com , chú ý bảng Sản phẩm

![N|Solid](https://i.imgur.com/yK4iTmq.png)
Ở đây cần chú ý code khi xử lý khi ta thêm 1 order_product thực tế ta sẽ chuyển thông tin từ class order_product sang json sau đó tiến hành endcode bằng Base64 , và sau khi mã hoá ta đẩy message đó lên hàng đợi , 
![N|Solid](https://i.imgur.com/z7FcWTu.png)
sau khi mã hoá ta kiểm tra màn hình console.log ra ta thấy có 1 chuỗi ký tự base64
![N|Solid](https://i.imgur.com/NpWpDhN.png)
thử kiểm tra trên serve Apache Active MQ5 , 
![N|Solid](https://i.imgur.com/BhSzlDt.png)
có thể kiểm tra bên trong messsage trong hàng đợi
![N|Solid](https://i.imgur.com/YCzNu81.png)
kiểm tra bên ngoài
![N|Solid](https://i.imgur.com/6KxETb2.png)
phần giải mã
![N|Solid](https://i.imgur.com/cgZhfsL.png)
màn hình sysout đã ra được json ta mong muốn 
![N|Solid](https://i.imgur.com/4G6NurU.png)
Kiểm tra lại trên Active MQ
![N|Solid](https://i.imgur.com/gED2r13.png)
Gửi email thông báo
![N|Solid](https://i.imgur.com/p0UqIkF.png)
nếu đơn hàng thành công
![N|Solid](https://i.imgur.com/1kuN8kX.png)
nếu đơn hàng thất bại
![N|Solid](https://i.imgur.com/CliExvr.png)




## Config

File application.properties ta config như sau
![N|Solid](https://i.imgur.com/Ggb1PRq.png)
Trong đó database dùng xampp port 3306
![N|Solid](https://i.imgur.com/I4TJh8x.png)
bấm admin để vào
![N|Solid](https://i.imgur.com/HvuHSEU.png)
Còn phần email chỗ spring.mail.username để tên gmail mình muốn gửi còn , spring.mail.password thì tìm mục app password trong mục security account google tạo 1 mật khẩu ứng dụng và gắn vô biến password
![N|Solid](https://i.imgur.com/T9VZTgP.png)
Ví dụ :
![N|Solid](https://i.imgur.com/khcJJJs.png)
