package nsc.utils;

import java.util.Date;

public class MStateGSon {
	private long		id;
	private int 		cliente;
	private int			sucursal;
	private int			sector;
	private String		nomenclador;
	private String		idtramite;
	private int			idestudio;
	private String		afiliado;
	private String 		apellido;
	private String		telefono;
	private int			edad;
	private String 		sexo;
	private String 		tipo;
	private int			osocial;
	private int 		categoria;
	private Date		festimada;
	private int			iduingreso;
	private Date		fingreso;
	private int			idufinaliza;
	private Date		ffinaliza;
	private int			iduentrega;
	private Date		fentrega;
	private String 		observaciones;
	private String 		estado;
	
	public MStateGSon(  long 		id,
						int 		cliente,
						int			sucursal,
						int			sector,
						String		nomenclador,
						String		idtramite,
						int			idestudio,
						String		afiliado,
						String 		apellido,
						String		telefono,
						int			edad,
						String 		sexo,
						String 		tipo,
						int			osocial,
						int 		categoria,
						Date		festimada,
						int			iduingreso,
						Date		fingreso,
						int			idufinaliza,
						Date		ffinaliza,
						int			iduentrega,
						Date		fentrega,
						String		observaciones,
						String		estado){
		
				this.id			=id;
				this.cliente	=cliente;
				this.sucursal	=sucursal;
				this.sector		=sector;
				this.nomenclador=nomenclador;
				this.idtramite	=idtramite;
				this.idestudio	=idestudio;
				this.afiliado	=afiliado;
				this.apellido	=apellido;
				this.telefono	=telefono;
				this.edad		=edad;
				this.sexo		=sexo;
				this.tipo		=tipo;
				this.osocial	=osocial;
				this.categoria	=categoria;
				this.festimada	=festimada;
				this.iduingreso	=iduingreso;
				this.fingreso	=fingreso;	
				this.idufinaliza=idufinaliza;
				this.ffinaliza	=ffinaliza;
				this.iduentrega	=iduentrega;
				this.fentrega	=fentrega;
				this.observaciones=observaciones;
				this.estado		=estado;
			
	}
	
	
	public void setId(long id){
		this.id=id;
	}
	public long getId(){
		return this.id;
	}
			
	public void setCliente(int cliente){
		this.cliente=cliente;
	}
	public int getCliente(){
		return this.cliente;
	}
	
	public void setSucursal(int sucursal){
		this.sucursal=sucursal;
	}
	public int getSucursal(){
		return this.sucursal;
	}
	
	public void setSector(int _sector){
		this.sector=_sector;
		
	}
	public int getSector()
	{
		return this.sector;
	}
	
	public void setNomenclador(String _nomenclador){
		this.nomenclador=_nomenclador;
		
	}
	public String getNomenclador()
	{
		return this.nomenclador;
	}	
	
	public void setIdtramite(String _idtramite){
		this.idtramite=_idtramite;
	}
	public String getIdtramite()
	{
		return this.idtramite;
	}
	
	public void setIdestudio(int _idestudio){
		this.idestudio=_idestudio;
	}
	public int getIdestudio()
	{
		return this.idestudio;
	}
	
	public void setAfiliado(String afiliado){
		this.afiliado=afiliado;
	}
	public String getAfiliado()
	{
		return this.afiliado;
	}
	
	public void setApellido(String _apellido){
		this.apellido=_apellido;
		
	}
	public String getApellido()
	{
		return this.apellido;
	}	
	
	public void setTelefono(String _telefono){
		this.telefono=_telefono;
		
	}
	public String getTelefono()
	{
		return this.telefono;
	}	
		
	public void setEdad(int _edad){
		this.edad=_edad;
		
	}
	public int getEdad()
	{
		return this.edad;
	}	
		
	public void setSexo(String _sexo){
		this.sexo=_sexo;
		
	}
	public String getSexo()
	{
		return this.sexo;
	}	
	
	public void setTipo(String _tipo){
		this.tipo=_tipo;
		
	}
	public String getTipo()
	{
		return this.tipo;
	}		
	
	public void setOsocial(int _osocial){
		this.osocial=_osocial;
		
	}
	public int getOsocial()
	{
		return this.osocial;
	}	
	
	public void setCategoria(int _categoria){
		this.categoria=_categoria;
		
	}
	public int getCategoria()
	{
		return this.categoria;
	}	
	
	public void setFestimada(Date festimada){
		this.festimada=festimada;
	}
	public Date getFestimada(){
		return this.festimada;
	}
	
	public void setIduingreso(int iduingreso){
		this.iduingreso=iduingreso;
	}
	public int getIduingreso(){
		return this.iduingreso;
	}
	
	public void setFingreso(Date fingreso){
		this.fingreso=fingreso;
	}
	public Date getFingreso(){
		return this.fingreso;
	}

	public void setIdufinaliza(int idufinaliza){
		this.idufinaliza=idufinaliza;
	}
	public int getIdufinaliza(){
		return this.idufinaliza;
	}
	
	public void setFfinaliza(Date ffinaliza){
		this.ffinaliza=ffinaliza;
	}
	public Date getFfinaliza(){
		return this.ffinaliza;
	}
	public void setIduentrega(int iduentrega){
		this.iduentrega=iduentrega;
	}
	public int getIduentrega(){
		return this.iduentrega;
	}
	public void setFentrega(Date fentrega){
		this.fentrega=fentrega;
	}
	public Date getFentrega(){
		return this.fentrega;
	}
	public void setObservaciones(String observaciones){
		this.observaciones=observaciones;
	}
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setEstado(String _estado){
		this.estado=_estado;
	}
	public String getEstado()
	{
		return this.estado;
	}
}
