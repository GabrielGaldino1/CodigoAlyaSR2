package br.gov.cesarschool.projeto.geral.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StringUtil implements Serializable{
	
	private StringUtil(){
		
	}
	
	public static boolean ehNuloOuBranco(String str){
		if(str == null) {
			return true;
		}
		String strVazia = str.trim();
		return strVazia.isEmpty();
	
	}
}