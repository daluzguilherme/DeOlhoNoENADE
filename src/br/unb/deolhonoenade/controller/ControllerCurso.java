package br.unb.deolhonoenade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import br.unb.deolhonoenade.DAO.ImportarBancoDeDados;
import br.unb.deolhonoenade.DAO.OperacoesBancoDeDados;
import br.unb.deolhonoenade.model.Curso;

public class ControllerCurso {
	
	private ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	private SQLiteDatabase db;
	private OperacoesBancoDeDados opBD;

	public ControllerCurso(Context context) {
		ImportarBancoDeDados bdados = new ImportarBancoDeDados(context);
		
		SQLiteDatabase database = bdados.openDataBase();
		
		this.opBD = new OperacoesBancoDeDados(database);
		
		this.db=database;
	}
	
	public SQLiteDatabase getDatabase(){
		return this.db;
	}
	
	public int buscaCodCurso(String nomeCurso){
		int codCurso;
		
		codCurso = this.opBD.getCodCurso(nomeCurso);
		
		return codCurso;
	}
	
	public ArrayList<Curso> buscaCurso(int codCurso, String uf){
		
		this.cursos = this.opBD.getCursos(codCurso, uf);
		
		return cursos;
	}
	
	private List<Curso> buscaCurso(int codCurso, String uf, int tipoInt) {
		this.cursos = this.opBD.getCursos(codCurso, uf, tipoInt);
		
		return cursos;
	}
	
	public ArrayList<Curso> buscaCurso(int codCurso, String uf, String municipio){
		
		this.cursos = this.opBD.getCursos(codCurso, uf, municipio);
		
		return cursos;
	}
	
	public ArrayList<Curso> buscaCurso(int codCurso, String uf, String municipio, String tipo){
		
		this.cursos = this.opBD.getCursos(codCurso, uf, municipio, tipo);
		
		return cursos;
	}
	
	public List<String> buscaCidades(int codCurso, String uf){
		List<String> cidades = new ArrayList<String>();
		cidades = this.opBD.getCidades(codCurso, uf);
		return cidades;
	}
	
	public List<String> buscaTipos(int codCurso, String municipio){
		List<String> tipos = new ArrayList<String>();
		tipos = this.opBD.getTipo(codCurso, municipio);
		return tipos;
	}

	public List<String> buscaUf(int codCurso) {
		List<String> ufs = new ArrayList<String>();
		ufs = this.opBD.getUfs(codCurso);
		return ufs;
	}
	/**
	private List<Curso> bucketSort(List<Curso> list){
		List<Curso> ate2 = new ArrayList<Curso>();
		List<Curso> ate3 = new ArrayList<Curso>();
		List<Curso> ate3_5 = new ArrayList<Curso>();
		List<Curso> ate4 = new ArrayList<Curso>();
		List<Curso> ate4_5 = new ArrayList<Curso>();
		List<Curso> ate5 = new ArrayList<Curso>();
		
		
		for (int i = 0; i < (list.size()); i++) {
			
			if (list.get(i).getConceitoEnade() < 2) {
				ate2.add(list.get(i));
			}else if (list.get(i).getConceitoEnade() < 3) {
				ate3.add(list.get(i));
			}else if (list.get(i).getConceitoEnade() < 4) {
				ate4.add(list.get(i));
			}else{
				ate5.add(list.get(i));
			}
			
		}
		
		list.clear();
		int z=0;
		do{
			if(ate5!=null){
				ate5 = this.ordenaListCurso(ate5);
				
			}
			if(ate4!=null){
				ate4 = this.ordenaListCurso(ate4);
				
			}
			if(ate3!=null){
				ate3 = this.ordenaListCurso(ate3);
				
			}
			if(ate2!=null){
				ate2 = this.ordenaListCurso(ate2);
				
			}
			z++;
			
		}while(z<10);
		
		list.addAll(ate5);
		list.addAll(ate4);
		list.addAll(ate3);
		list.addAll(ate2);
		
		return list;
	}
	
	private List<Curso> ordenaListCurso(List<Curso> list){
		
		Curso aux;
		int j=0;
		
		
		do {
			//Dividir em sublistas e fazer a organizacao delas

			for (int i = 0; i < (list.size() - 1); i++) {
				if (list.get(i).getConceitoEnade() < list.get(i + 1)
						.getConceitoEnade()) {
					aux = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, aux);
					
				}

			}
			for (int i = list.size() - 1; i >= 1; i--) {
				if (list.get(i).getConceitoEnade() > list.get(i - 1)
						.getConceitoEnade()) {
					aux = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, aux);
				}
			}
			
			j++;
			
			
		} while (j<5);
			
		
		
		return list;
	}*/
	
	private List<Curso> exchange(List<Curso> list, int i, int j){
		Curso aux;
		
		aux = list.set(i, list.get(j));
		list.set(j, aux);
		
		return list;
	}
	
	private List<Curso> maxheap(List<Curso> list, int i, int n){
		int left = 2*i;
		int right = 2*i+1;
		
		int largest;
		
		if(left <= n && 
		 list.get(left).getConceitoEnade() > list.get(i).getConceitoEnade()){
			largest = left;
		}else{
			largest=i;
		}
		
		if(right <= n && 
				 list.get(right).getConceitoEnade() > list.get(largest).getConceitoEnade()){
			largest = right;
		}
		
		if(largest!=i){
			list = exchange(list, i, largest);
			list = maxheap(list, largest, n);
		}
		
		return list;
	}
	
	private List<Curso> biuldheap(List<Curso> list){
		int n = list.size() - 1;
		
		for(int i = n/2; i>=0; i--){
			list = maxheap(list, i, n);
		}
		
		return list;
	}
	
	private List<Curso> organiza(List<Curso> list){
		biuldheap(list);
		
		int n = list.size()-1;
		for(int i=list.size()-1;i>0;i--){
			list = exchange(list, 0, i);
			n--;
			list = maxheap(list, 0, n);
		}
		
		Collections.reverse(list);
		
		return list;
	}
	

	public List<String> buscaStringCurso(int codCurso2, String uf2) {
		List<String> cursos = new ArrayList<String>();
		List<Curso> listaCursos = new ArrayList<Curso>();
		
		listaCursos = this.buscaCurso(codCurso2, uf2);
		
		listaCursos = this.organiza(listaCursos);
		//listaCursos = this.bucketSort(listaCursos);
		
		for (int i = 0; i <listaCursos.size(); i++) {
			cursos.add(String.format("%s - %f", listaCursos.get(i).getIES().getNome(),
					listaCursos.get(i).getConceitoEnade()));
		}
		
		return cursos;
	}

	public List<String> buscaStringCurso(int codCurso2, String uf2,
			String municipio2, String tipo2) {
		
		List<String> cursos = new ArrayList<String>();
		List<Curso> listaCursos = new ArrayList<Curso>();
		
		listaCursos = this.buscaCurso(codCurso2, uf2, municipio2, tipo2);
		
		listaCursos = this.organiza(listaCursos);
		//listaCursos = this.ordenaListCurso(listaCursos);
		
		for (int i = 0; i <listaCursos.size(); i++) {
			cursos.add(String.format("%s - %f", listaCursos.get(i).getIES().getNome(),
					listaCursos.get(i).getConceitoEnade()));
		}
		
		return cursos;
	}

	public List<String> buscaStringCurso(int codCurso2, String uf2,
			String municipio2) {
		
		List<String> cursos = new ArrayList<String>();
		List<Curso> listaCursos = new ArrayList<Curso>();
		
		listaCursos = this.buscaCurso(codCurso2, uf2, municipio2);
		
		listaCursos = this.organiza(listaCursos);
		//listaCursos = this.ordenaListCurso(listaCursos);
		
		for (int i = 0; i <listaCursos.size(); i++) {
			cursos.add(String.format("%s - %f", listaCursos.get(i).getIES().getNome(),
					listaCursos.get(i).getConceitoEnade()));
		}
		
		return cursos;
	}

	public List<String> buscaStringCurso(int codCurso, String uf, int tipoInt) {
		List<String> cursos = new ArrayList<String>();
		List<Curso> listaCursos = new ArrayList<Curso>();
		
		
		listaCursos = this.buscaCurso(codCurso, uf, tipoInt);
		
		listaCursos = this.organiza(listaCursos);
		//listaCursos = this.ordenaListCurso(listaCursos);
		
		for (int i = 0; i <listaCursos.size(); i++) {
			cursos.add(String.format("%s - %f", listaCursos.get(i).getIES().getNome(),
					listaCursos.get(i).getConceitoEnade()));
		}
		
		return cursos;
	}

	
	
/*
	private SQLiteDatabase openDatabase(Context context) {
		ImportarBancoDeDados db = new ImportarBancoDeDados(context);
        return db.openDataBase();
	}
*/
}
