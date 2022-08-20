package br.com.bookstore.domain.enums;

public enum Status {
	
	ATIVADO(0, "ROLE_ATIVADO"),DESATIVADO(1, "ROLE_DESATIVADO");
	
	private Integer codigo;
	private String descricao;
	
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Status toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		//PARA CADA STATUS X DENTRO DO NOSSO STATUS.VALUES(VALORES NO CASO 0 = ATIVADO, e 1= DESATIVADO)
		for(Status x : Status.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido");
		
	}

}
