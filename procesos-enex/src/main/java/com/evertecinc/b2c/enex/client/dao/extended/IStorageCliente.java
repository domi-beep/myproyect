package com.evertecinc.b2c.enex.client.dao.extended;

import java.util.List;

import com.evertecinc.b2c.enex.client.model.dto2.ContratoStorageDTO;

public interface IStorageCliente {
	
	public List<ContratoStorageDTO> getListStorageCliente(Long idClient);

}
