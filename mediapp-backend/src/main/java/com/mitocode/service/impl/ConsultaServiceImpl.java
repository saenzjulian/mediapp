package com.mitocode.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IConsultaService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService{
	
	// Como necesito una instancia (new) y el IoC la tiene la llamo con la anotación
	@Autowired
	private IConsultaRepo repo;
	
	@Autowired
	private IConsultaExamenRepo ceRepo;	

	@Override
	protected IGenericRepo<Consulta, Integer> getRepo() {
		return repo;
	}
	
	/*
	 * La anotación @Transactional hace que la atomicidad de los datos se cumpla, es decir,
	 * si ocurre un error con alguno de los querys se acciona un rollback para no insertar datos basura
	 */
	@Transactional
	@Override
	public Consulta registroTransaccional(ConsultaListaExamenDTO dto) {
		// Insertar la consulta para ontener llave primaria
		// Insertar el detalle consulta usando la llave primaria anterior
		dto.getConsulta().getDetalleConsulta().forEach(detalle -> detalle.setConsulta(dto.getConsulta()));
		
		repo.save(dto.getConsulta());
		
		dto.getListExamen().forEach(ex -> ceRepo.registrar(dto.getConsulta().getIdConsulta(), ex.getIdExamen()));
		
		return dto.getConsulta(); 
	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) { 
		return repo.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(LocalDateTime fecha) { 
		return repo.buscarFecha(fecha, fecha.plusDays(1));
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		//List<Object[]>
		//cantidad	fecha
		//[1	,		"15/05/2021"]
		//[2	,		"22/05/2021"]
		//[5	,		"24/04/2021"]
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		repo.listarResumen().forEach(x -> {
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		return consultas;
	}

	@Override
	public byte[] generarReporte() {
		byte[] data = null;

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("txt_titulo", "Prueba de titulo");

		try {
			File file = new ClassPathResource("/reports/consultas.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), parametros, new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
			// mitocode jasperreports | excel, pdf, ppt, word, csv
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
