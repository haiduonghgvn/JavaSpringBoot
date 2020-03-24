package com.example.demo.repository;

        import com.example.demo.entity.Image;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import javax.persistence.criteria.From;
        import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "select *  from storeau.images i where i.product_id = :id" ,nativeQuery = true)
    public List<Image>  findImageByProductId(@Param("id") long productID);

}
