package com.brunogago.crud.entities;

import java.io.Serializable;

import org.modelmapper.ModelMapper;

import com.brunogago.crud.data.vo.ProductVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable{

	private static final long serialVersionUID = -1181054478653373316L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	
	@Column(name = "stock", nullable = false, length = 10)
	private Integer stock;
	
	@Column(name = "price", nullable = false, length = 10)
	private double price;

	//Método responsável por receber um ProductVO e transformar para um Product
	public static Product create(ProductVO productVO) {
		return new ModelMapper().map(productVO, Product.class);
	}
}
