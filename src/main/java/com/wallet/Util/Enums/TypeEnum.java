package com.wallet.Util.Enums;

public enum TypeEnum {

	EN("Entrada"),
	SD("SAÍDA");
	
	private final String value;
	
	TypeEnum(String value){
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
	
	public static TypeEnum getEnum(String value) {
		for(TypeEnum t : values()) {
			if(value.equals(t.getValue())) {
				return t;
			}
		}
		return null;
	}
}
