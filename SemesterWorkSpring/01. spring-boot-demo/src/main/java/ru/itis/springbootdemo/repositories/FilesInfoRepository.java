package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springbootdemo.models.Image;

@Repository
public interface FilesInfoRepository extends JpaRepository<Image, Long> {
    Image findByAndStorageFileName(String fileName);
}
