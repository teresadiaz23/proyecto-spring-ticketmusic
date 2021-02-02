package com.salesianostriana.dam.ticketmusic.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Usuario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8965437056417704456L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellidos;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private String localidad;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private boolean admin;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private List<Venta> ventas;
	
	
	public Usuario(String nombre, String apellidos, LocalDate fechaNacimiento, String localidad, String email,
			String password, boolean admin) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.localidad = localidad;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}
	
	//Métodos helpers Venta
	public void addVenta(Venta v) {
		this.ventas.add(v);
		v.setUsuario(this);
		
		
	}
	
	public void removeVenta(Venta v) {
		this.ventas.remove(v);
		v.setUsuario(null);
		
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = "ROLE_";
		if (admin) {
			role += "ADMIN";
		} else {
			role += "USER";
		}
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}


	@Override
	public String getUsername() {
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}



	
	
	
	

}
