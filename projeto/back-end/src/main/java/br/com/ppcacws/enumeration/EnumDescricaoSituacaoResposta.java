package br.com.ppcacws.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum EnumDescricaoSituacaoResposta {

	C("Certo"), E("Errado");

    private String descricaoSituacaoReposta;

	private static final Map<String, EnumDescricaoSituacaoResposta> constanteToEnum = new HashMap<String, EnumDescricaoSituacaoResposta>();	
    
    EnumDescricaoSituacaoResposta(String descricaoSituacaoReposta) {
        
    		this.descricaoSituacaoReposta = descricaoSituacaoReposta;
    }
    
    public String getDescricaoSituacaoReposta() {
		return descricaoSituacaoReposta;
	}
    
    static {

		for (EnumDescricaoSituacaoResposta descricoesSituacaoResposta : values())
			constanteToEnum.put(descricoesSituacaoResposta.name(), descricoesSituacaoResposta);
	}
    
    public static EnumDescricaoSituacaoResposta fromOrdinal(int id) {

		if (id < values().length)
			return values()[id];

		return null;
	}
    
    public static EnumDescricaoSituacaoResposta fromConstante(String constanteEnum) {
		
		return constanteToEnum.get(constanteEnum);
	}
	
}
