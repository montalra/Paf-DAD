package com.marketing.marcas.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marketing.marcas.entity.Marcas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WrapperResponse <T>{
	private Boolean ok; 
	private String message;
	private T body;
	
	
	public ResponseEntity<WrapperResponse<T>> createResponse(){
		return new ResponseEntity<>(this,HttpStatus.OK);
	}
	public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus code){
		return new ResponseEntity<>(this, code);
	}
}
