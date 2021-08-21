package vn.codegym.repository;

import org.springframework.stereotype.Repository;
import vn.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private static List<Product> list = new ArrayList<>();
    static {
        list.add(new Product(1, "Hoa Hồng", 11111, "/image/bo-hoa-ve-dep-dac-biet-83.jpg", 100000,
                "Cây hoa hồng nhung thuộc họ Hồng truyền thống của Việt Nam- Rosace.\""));
        list.add(new Product(2, "Hạnh phúc bất tận 2", 11044, "/image/bo-hoa-ve-dep-dac-biet-89.jpg", 150000,
                "Hạnh phúc bất tận chính là lúc bạn tìm kiếm được một nửa trái tim yêu thương còn lại trong hàng tỉ người trên thế giới này. Một người thực sự yêu thương bạn vô điều kiện và đó được gọi là hạnh phúc. Hãy trân trọng người ấy bằng cả cuộc đời của bạn nhé!"));
        list.add(new Product(3, "Bình hồng vàng AT 30", 11045, "/image/bo-hoa-ve-dep-dac-biet-90.jpg", 200000,
                "Hoa hồng Đà Lạt theo mùa."));
        list.add(new Product(4, "Mùa xuân của mẹ", 11046, "/image/bo-hoa-ve-dep-dac-biet-91.jpg", 300000,
                "Hoa hồng Đà Lạt theo mùa."));
        list.add(new Product(5, "Love Mondial", 11047, "/image/bo-hoa-ve-dep-dac-biet-92.jpg", 350000,
                "Tình yêu của mẹ dành cho các con là một tình yêu vô giá và bất diệt. Không một từ ngữ nào có thể lột tả được hết sự lớn lao của tình mẫu tử. Và không có một món quà vật chất nào có thể bù đắp lại tình yêu ấy. Vì vậy, nhân các ngày đặc biệt hoặc dịp lễ, chúng ta đừng ngại ngần mà hãy dành tặng những cành hoa xinh đẹp nhất tặng cho người mẹ yêu quý của mình. Những bông hoa sẽ thay bạn gửi gắm những cảm xúc mà lâu nay khó nói thành lời từ bạn."));
        list.add(new Product(6, "Nắng Hồng", 11048, "/image/bo-hoa-ve-dep-dac-biet-93.jpg", 600000,
                "Pink Mondial là những bông hồng nổi tiếng nhập khẩu từ Ecuador. Với sự hòa quyện độc đáo giữa màu hồng phớt, chút trắng tinh tế và sắc cam nhẹ bí ẩn ở trung tâm, những bông Pink Mondial đại diện cho một tâm hồn tư do, nghệ sĩ và phóng khoáng nên khi nở hoa tự tin phô diễn hết sức sống và vẻ đẹp mang trong mình, chẳng chút ngại ngần hay rụt rè."));
        list.add(new Product(7, "Thanh lịch", 11049, "/image/bo-hoa-ve-dep-dac-biet-94.jpg", 670000,
                "Chọn tông hồng làm màu sắc chủ đạo, “Nắng hồng” tạo nên một tổng thể nhẹ nhàng, khơi gợi cảm giác thư thái, vui tươi khi ngắm nhìn. Sản phẩm gửi gắm thông điệp: Tình yêu vốn dĩ không phải là điều ta nhận đuợc mà là những gì ta cho đi. Hãy chia sẻ yêu thương khi ta còn có thể vì chính tình yêu sẽ làm cuộc sống thêm tươi vui, hạnh phúc!"));
        list.add(new Product(8, "Sum họp", 11050, "/image/bo-hoa-ve-dep-dac-biet-95.jpg", 450000,
                "Pink Mondial là những bông hồng nổi tiếng nhập khẩu từ Ecuador. Với sự hòa quyện độc đáo giữa màu hồng phớt, chút trắng tinh tế và sắc cam nhẹ bí ẩn ở trung tâm, những bông Pink Mondial đại diện cho một tâm hồn tư do, nghệ sĩ và phóng khoáng nên khi nở hoa tự tin phô diễn hết sức sống và vẻ đẹp mang trong mình, chẳng chút ngại ngần hay rụt rè."));
        list.add(new Product(9, "Mây hồng", 11051, "/image/bo-hoa-ve-dep-dac-biet-96.jpg", 410000,
                "Mây Hồng gồm có loài hồng da ngọt ngào tựa những áng mây lung linh. Gửi tặng em người con gái dịu hiền và lời nhắn \"...hãy mãi lưu giữ nét ngây thơ, trong sáng và trẻ trung của tuổi thanh xuân em nhé...\" Thích hợp để tặng sinh nhật, kỉ niệm,..."));
        list.add(new Product(10, "Nắng hạ", 11052, "/image/bo-hoa-ve-dep-dac-biet-97.jpg", 230000,
                "Mùa hè xuất hiện với những ánh nắng gay gắt, những bông hoa nhiệt đới đua nhau nở rộ. Đó cũng là mùa chia tay cuối cấp, mùa của những niềm vui du lịch cùng gia đình và người yêu. Ánh nắng chói chang đó làm cho tình yêu đôi lứa cháy bỏng. Giỏ hoa tone vàng - xanh giúp bạn có sự lựa chọn quà tặng dành cho những người có sinh nhật vào mùa Hạ"));
        list.add(new Product(11, "Pink Box", 11053, "/image/bo-hoa-ve-dep-dac-biet-98.jpg", 800000,
                "Hộp hoa hồng với sắc hồng tươi tắn, ngọt ngào kết hợp cùng với lá bạc xanh, tạo nên một tổng thể vô cùng hài hoà mà không kém phần sang trọng. Hộp hoa phù hợp tặng cho người yêu, bạn bè, đối tác, khách hàng là nữ. Chắc chắn đây sẽ là món quà vô cùng bất ngờ dành cho người bạn đang quan tâm đấy."));

    }
    @Override
    public List<Product> getAll() {
        return list;
    }

    @Override
    public void save(Product product) {
        if (product.getId() == 0){
            list.add(product);
        }else {
            for (int i = 0; i<list.size(); i++){
                if (list.get(i).getId() == product.getId()){
                    list.set(i, product);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getId() == id){
                list.remove(i);
                break;
            }
        }
    }

    @Override
    public Product findById(int id) {
        for (Product product : list){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }


    @Override
    public Product getFirstProduct(Set<Product> list) {
        if (list.isEmpty()){
            return null;
        }
        for (Product product : list){
            return product;
        }
        return null;
    }

    @Override
    public boolean existById(Set<Product> list, int id) {
        for (Product product : list){
            if (product.getId() == id){
                return true;
            }
        }
        return false;
    }
}
