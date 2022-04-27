package com.BookIng.bookCart.vo;

public class BookCartVO {
	
	private long cartNo, bookNo, quantity, bookCount;
	private String id, title, writer, cover, genre;
	private long price, totalPrice;
	
	public long getCartNo() {
		return cartNo;
	}
	public void setCartNo(long cartNo) {
		this.cartNo = cartNo;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getBookCount() {
		return bookCount;
	}
	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "BookCartVO [cartNo=" + cartNo + ", bookNo=" + bookNo + ", quantity=" + quantity + ", bookCount="
				+ bookCount + ", id=" + id + ", title=" + title + ", writer=" + writer + ", cover=" + cover + ", genre="
				+ genre + ", price=" + price + ", totalPrice=" + totalPrice + "]";
	}
	
}
