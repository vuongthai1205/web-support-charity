# webhotrotuthien
git clone https://github.com/vuongthai1205/web-support-charity.git

Các bước run server redis
1. Tải docker
2. Truy cập vào sourcecode và nhập command line là “docker compose up”


Các bước nhập database
1. Cài đặt mysql server
2. mở mysql workbench
3. tạo schema mới | tạo mới 1 database và đặt tên webhotrotuthien
4. chọn server -> data import và import file database.sql

Các bước sử dụng và chạy server
1. tải netbean 13 trở lên và jdk 14 trở lên và tomcat server 9
2. mở project trong netbean
3. sau đó chỉnh sửa username và password của mysqlserver trong file database.properties
4. nhấn build dự án
5. cuối cùng nhấn run

Các bước sử dụng và chạy client

1. Cài đặt node phiên bản 16.14.2
2. Mở thư mục webhotrotuthien_client trong vs code hoặc bất kỳ ide nào 
3. Chạy câu lệnh npm i sau đó chay câu lệnh npm start. Lưu ý phải chạy server trước mới chạy client.
