package com.alcorcode.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// mongoDb alcorcode veritabanına Student Collections ekle
	@Bean
	CommandLineRunner runner(
			StudentRepository repository, MongoTemplate mongoTemplate){

		return args -> {
			Address address = new Address(
					"Turkey",
					"Ankara",
					"A06"
			);
			String email = "asya@gmail.com";

			Student student = new Student(
					"Asya",
					"Turgut",
					email,
					Gender.FEMALE,
					address,
					List.of("Computer Science","Maths"),
					BigDecimal.TEN,
					LocalDateTime.now() // ZoneDateTıme kullanmıştık hata aldık.LocalDateTime kullanmalıyız.
			);

//			usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

			// MongoTemplate yerine StudentRepository interface kullandık.
			// (extends MongoRepository)
			repository.findStudentByEmail(email)
					.ifPresentOrElse( s -> {
						// isExists part
						System.out.println(s + " already exists");
					},() -> {
						// isEmpty kısmı
						System.out.println("Inserting student " + student);
						repository.insert(student);
					});
		};

	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		// Creating custom query
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);
		// 1 den fazla aynı email var ise durumu
		if(students.size() > 1 ){
			throw new IllegalStateException(
					"found many students with email " + email);
		}
		// email yok ise durumu
		if(students.isEmpty()){
			System.out.println("Inserting student " + student);
			repository.insert(student);
		}else{
			System.out.println(student + " already exists");
		}
	}
}
