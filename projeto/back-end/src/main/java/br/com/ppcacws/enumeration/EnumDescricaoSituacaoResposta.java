package br.com.ppcacws.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum EnumDescricaoSituacaoResposta {

	CERTO("C", "Certo"), ERRADO("E", "Errado");

	private String situacaoReposta;
	private String descricaoSituacaoReposta;

	private static final Map<String, EnumDescricaoSituacaoResposta> constanteToEnum = new HashMap<String, EnumDescricaoSituacaoResposta>();
	private static final Map<String, EnumDescricaoSituacaoResposta> situacaoToEnum = new HashMap<String, EnumDescricaoSituacaoResposta>();
	private static final Map<String, EnumDescricaoSituacaoResposta> descricaoSituacaoToEnum = new HashMap<String, EnumDescricaoSituacaoResposta>();

	EnumDescricaoSituacaoResposta(String situacaoResposta, String descricaoSituacaoReposta) {

		this.situacaoReposta = situacaoResposta;
		this.descricaoSituacaoReposta = descricaoSituacaoReposta;
	}

	public String getSituacaoReposta() {
		return situacaoReposta;
	}

	public String getDescricaoSituacaoReposta() {
		return descricaoSituacaoReposta;
	}

	static {

		for (EnumDescricaoSituacaoResposta enumDescricaoSituacaoResposta : values())
			constanteToEnum.put(enumDescricaoSituacaoResposta.name(), enumDescricaoSituacaoResposta);
	}

	static {

		for (EnumDescricaoSituacaoResposta enumDescricaoSituacaoResposta : values())
			situacaoToEnum.put(enumDescricaoSituacaoResposta.getSituacaoReposta(), enumDescricaoSituacaoResposta);
	}

	static {

		for (EnumDescricaoSituacaoResposta enumDescricaoSituacaoResposta : values())
			descricaoSituacaoToEnum.put(enumDescricaoSituacaoResposta.getDescricaoSituacaoReposta(), enumDescricaoSituacaoResposta);
	}

	public static EnumDescricaoSituacaoResposta fromOrdinal(int id) {

		if (id < values().length)
			return values()[id];

		return null;
	}

	public static EnumDescricaoSituacaoResposta fromConstante(String constanteEnum) {

		return constanteToEnum.get(constanteEnum);
	}

	public static EnumDescricaoSituacaoResposta fromSituacaoResposta(String situacaoResposta) {

		return situacaoToEnum.get(situacaoResposta);
	}

	public static EnumDescricaoSituacaoResposta fromDescricaoSituacaoResposta(String descricaoSituacaoResposta) {

		return descricaoSituacaoToEnum.get(descricaoSituacaoResposta);
	}

}
