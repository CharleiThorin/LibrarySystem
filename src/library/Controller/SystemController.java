package library.Controller;

import com.jfoenix.controls.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import library.Model.*;
import library.data.DataAccess;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SystemController implements Initializable {
	@FXML
	private JFXButton checkout;

	private boolean isBook = true;

	@FXML
	private JFXComboBox<String> combo;

	@FXML
	private JFXComboBox<String> combo2;

	@FXML
	private TableView<Book> book_table;

	@FXML
	private TableColumn<Book, String> col_isbn;

	@FXML
	private TableColumn<Book, String> col_title;

	@FXML
	private TableColumn<Book, String> col_author;

	@FXML
	private TableView<Person> memeber_table; // Abrha

	@FXML
	private TableColumn<LibraryMember, String> col_fname;
	@FXML
	private TableColumn<LibraryMember, String> col_lname;

	@FXML
	private TableColumn<LibraryMember, String> col_phone;

	@FXML
	private TableColumn<LibraryMember, String> col_address;

	@FXML
	private TableView<RecData> rec_book_table1;

	@FXML
	private TableColumn<RecData, String> rec_col_isbn;

	@FXML
	private TableColumn<RecData, String> rec_col_title;

	@FXML
	private TableColumn<RecData, String> rec_col_author;

	@FXML
	private TableColumn<RecData, String> rec_col_due;

	@FXML
	private TableColumn<RecData, String> rec_col_name;

	@FXML
	private JFXButton add;

	@FXML
	private StackPane anchorp;

	@FXML
	private BorderPane support;

	@FXML
	private StackPane stackpaneBody;

	public ObservableList<Book> books;

	private ObservableList<Person> members; // Abrha

	DataAccess dataAccess = DataAccess.getInstance();
	public static Employee currentUser = new Employee(UserRole.LIBRARIAN, "sol");

	// public void login() {
	// this.currentUser = new Employee(UserRole.ADMIN, "Sol");
	// }

	public void initMemberTable() {
		initMemberCols();
	}

	public void initMemberCols() {
		col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));

		if (currentUser.getAccess() == UserRole.ADMIN || currentUser.getAccess() == UserRole.BOTH) {
			editMemberCols();
		}
	}

	public void editMemberCols() {
		col_fname.setCellFactory(TextFieldTableCell.forTableColumn());
		col_fname.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirstName(e.getNewValue());
		});
		col_lname.setCellFactory(TextFieldTableCell.forTableColumn());
		col_lname.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setLastName(e.getNewValue());
		});

		col_phone.setCellFactory(TextFieldTableCell.forTableColumn());
		col_phone.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhoneNumber(e.getNewValue());
		});

		col_address.setCellFactory(TextFieldTableCell.forTableColumn());
		col_address.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).getAddress().setStreet(e.getNewValue());
		});

		memeber_table.setEditable(true);
	}

	public void initRecTable() {
		rec_col_isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
		rec_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		rec_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
		rec_col_due.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		rec_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

	}

	public void initBookTable() {
		initBookCols();
	}

	public void initBookCols() {
		col_isbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
		if (currentUser.getAccess() == UserRole.ADMIN || currentUser.getAccess() == UserRole.BOTH) {
			editBookCols();
		}

	}

	public void editBookCols() {
		col_isbn.setCellFactory(TextFieldTableCell.forTableColumn());
		col_isbn.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setISBN(e.getNewValue());
		});

		col_title.setCellFactory(TextFieldTableCell.forTableColumn());
		col_title.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitle(e.getNewValue());
		});

		col_author.setCellFactory(TextFieldTableCell.forTableColumn());
		col_author.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setAuthor(e.getNewValue());
		});
		final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		book_table.setEditable(true);
	}

	public void loadOverDueBooks() {
		ObservableList<Book> overdueBooks = FXCollections.observableArrayList();
		// for(Book book: books) {
		// if(book.getDueDate().isBefore(LocalDate.now())) {
		// overdueBooks.add(book);
		// }
		// }

		book_table.setItems(overdueBooks);

	}

	public void loadBooks() {
		books = FXCollections.observableArrayList(dataAccess.getBooks());
		book_table.setItems(books);
		book_table.setItems(books);
	}

	public void loadMembers() {
		members = FXCollections.observableArrayList(dataAccess.getMembers());
		memeber_table.setItems(members);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initMemberTable();
		loadMembers();
		initBookTable();
		loadBooks();
		initRecTable();

		checkout.disableProperty().bind(Bindings.isEmpty(book_table.getSelectionModel().getSelectedItems()));
		// checkout.disableVisualFocusProperty().bind(Bindings.isEmpty(combo.getValue().equals("Members")));

		combo.getSelectionModel().selectFirst();
		combo2.getSelectionModel().selectFirst();
		combo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("Members")) {
					refreshMemberTable();
					checkout.setVisible(false);
					book_table.setVisible(false);
					rec_book_table1.setVisible(false);
					memeber_table.setVisible(true);
					combo2.setVisible(false);
					isBook = false;
					add.setText("Add");
				} else {

					book_table.setVisible(true);
					memeber_table.setVisible(false);
					combo2.setVisible(true);
					isBook = true;
					add.setText("Add");
				}
			}
		});

		combo2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("All")) {
					rec_book_table1.setVisible(false);
					book_table.setVisible(true);
					book_table.setItems(books);
				} else if (newValue.equals("Checkout records")) {
					System.out.println(dataAccess.getAllRecords().size());

					rec_book_table1.setItems(dataAccess.getAllRecords());
					rec_book_table1.setVisible(true);
					book_table.setVisible(false);
				} else {
					// loadOverDueBooks();
				}
			}
		});

		// book_table.get
	}
	//
	// public void addMember(LibraryMember m) {
	// members.add(m);
	// memeber_table.setItems(members);
	// }
	//
	// public void addBook(Book b) {
	// books.add(b);
	// book_table.setItems(books);
	// }

	TextArea textArea;

	// Pane pane;
	@FXML
	void add(ActionEvent event) {
		JFXDialogLayout content = new JFXDialogLayout();
		VBox vBox = new VBox();
		System.out.println(combo.getValue());

		if (combo.getValue().equals("Books")) {

			Label text = new Label("New Book");
			text.setMinWidth(160);
			text.setMinHeight(63);
			text.setStyle("-fx-font-family: Calibri;" + "-fx-font-weight: BOLD;" + "-fx-font-size: 40");
			content.setHeading(text);

			JFXTextField bookTitle = new JFXTextField();
			bookTitle.setPromptText("Title");

			JFXTextField bookISBN = new JFXTextField();
			bookISBN.setPromptText("ISBN");

			JFXTextField author = new JFXTextField();
			author.setPromptText("Author");

			JFXComboBox numberOfCopies = new JFXComboBox();
			int[] numberOfBooks = { 1, 2, 3, 4, 5, 6, 7, 8, };
			numberOfCopies.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);

			vBox.setMaxWidth(300);
			vBox.setMinWidth(300);
			vBox.setSpacing(10);
			vBox.getChildren().addAll(bookTitle, bookISBN, author, numberOfCopies);

			JFXButton buttonBook = new JFXButton();
			buttonBook.setStyle("-fx-background-radius: 5.0;" + "-fx-background-color: BLUE;" + "-fx-font-weight: 20.0;"
					+ "-fx-text-fill: white");

			buttonBook.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
			buttonBook.setPrefHeight(32);
			buttonBook.setPrefWidth(150);
			buttonBook.setText("Save");

			content.setMaxWidth(350);
			content.setMinWidth(350);
			content.setBody(vBox);
			content.setPrefSize(600, 300);
			content.setActions(buttonBook);
			anchorp = new StackPane();
			// StackPane stackPane1 = new StackPane();
			JFXDialog dialog = new JFXDialog(stackpaneBody, content, JFXDialog.DialogTransition.LEFT, true);
			dialog.show();
			buttonBook.setOnAction(e -> {
				String title = bookTitle.getText();
				String isbn = bookISBN.getText();
				String a = author.getText();
				int numCopies = (int) numberOfCopies.getSelectionModel().getSelectedItem();
				addNewBook(new Book(title, a, isbn), numCopies);
				refreshBookTable();
				dialog.close();
			});

		} else if (combo.getValue().equals("Members")) {
			Label text = new Label("New LibraryMember");
			text.setMinWidth(160);
			text.setMinHeight(63);
			text.setStyle("-fx-font-family: Calibri;" + "-fx-font-weight: BOLD;" + "-fx-font-size: 40");
			content.setHeading(text);

			JFXTextField firstName = new JFXTextField();
			firstName.setPromptText("First Name");
			firstName.setLabelFloat(true);

			JFXTextField lastName = new JFXTextField();
			lastName.setPromptText("Last Name");
			lastName.setLabelFloat(true);

			JFXTextField phone = new JFXTextField();
			phone.setPromptText("Phone Number");
			phone.setLabelFloat(true);

			JFXTextField address = new JFXTextField();
			address.setPromptText("Address");
			address.setLabelFloat(true);

			JFXButton buttonMember = new JFXButton();
			buttonMember.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
			buttonMember.setPrefHeight(32);
			buttonMember.setMinWidth(150);
			buttonMember.setText("Save");

			vBox.setMaxWidth(300);
			vBox.setMinWidth(300);
			vBox.setSpacing(10);
			vBox.getChildren().addAll(firstName, lastName, phone, address);

			buttonMember.setStyle("-fx-background-radius: 5.0;" + "-fx-background-color: BLUE;"
					+ "-fx-font-weight: 20.0;" + "-fx-text-fill: white");

			content.setMaxWidth(350);
			content.setMinWidth(350);
			content.setBody(vBox);
			content.setPrefSize(600, 300);
			content.setActions(buttonMember);
			anchorp = new StackPane();
			// StackPane stackPane1 = new StackPane();
			JFXDialog dialog = new JFXDialog(stackpaneBody, content, JFXDialog.DialogTransition.LEFT, true);
			dialog.show();
			buttonMember.setOnAction(e -> {
				String fname = firstName.getText();
				String lname = lastName.getText();
				String phoneNo = phone.getText();
				String addr = address.getText();
				String[] adr = addr.split(" ");
				
				System.out.println(addr);

				String strt = adr[0];
				String cty = adr[1];
				String stt = adr[2];
				String zipcd = adr[3];
				// LibraryMember m = new LibraryMember(fname,lname, phoneNo, new
				// Address(strt,cty,Integer.parseInt(zipcd))); // Commented by Abrha

				// Added by Abrha
				Person libraryMember = new LibraryMember(1001, fname, lname, phoneNo,
						new Address(strt, cty, stt, zipcd));

				dataAccess.addMember(libraryMember);
				refreshMemberTable();
				dialog.close();
				// stackpaneBody.getChildren().remove(stackPane1);
			});

		}

	}

	public void checkoutbooks(ActionEvent actionEvent) {

		JFXDialogLayout content = new JFXDialogLayout();
		VBox vBox = new VBox();
		System.out.println(combo.getValue());
		// if (combo.getValue().equals("Books")) {
		Label text = new Label("Checkout Book");
		text.setMinWidth(160);
		text.setMinHeight(63);
		text.setStyle("-fx-font-family: Calibri;" + "-fx-font-weight: BOLD;" + "-fx-font-size: 40");
		content.setHeading(text);

		JFXTextField bookTitle = new JFXTextField();
		bookTitle.setEditable(false);
		bookTitle.setMinHeight(25);
		bookTitle.setLabelFloat(true);
		bookTitle.setPromptText("Title");
		bookTitle.setText(book_table.getSelectionModel().getSelectedItem().getTitle());

		JFXTextField bookISBN = new JFXTextField();
		bookISBN.setEditable(false);
		bookISBN.setMinHeight(25);
		bookISBN.setLabelFloat(true);
		bookISBN.setPromptText("ISBN");
		bookISBN.setText(book_table.getSelectionModel().getSelectedItem().getISBN());

		JFXTextField author = new JFXTextField();
		author.setEditable(false);
		author.setMinHeight(25);
		author.setLabelFloat(true);
		author.setPromptText("Author");
		author.setText(book_table.getSelectionModel().getSelectedItem().getAuthor());

		JFXTextField bookID = new JFXTextField();
		bookID.setEditable(false);
		bookID.setMinHeight(25);
		bookID.setLabelFloat(true);
		bookID.setPromptText("CopyID");
		bookID.setText(book_table.getSelectionModel().getSelectedItem().getOneCopy().getCopyId() + "");

		HBox hBox = new HBox(20);
		hBox.getChildren().addAll(bookTitle, bookISBN);

		HBox hBox1 = new HBox(20);
		hBox1.getChildren().addAll(author, bookID);

		dataAccess.addMember(new LibraryMember(1001, "Brook", "Yemerou", "641-111-1111",
				new Address("1000N 4th street", "FairField", "IWOA", "52556")));
		List<String> memberNames = new ArrayList<>();
		for (Person member : dataAccess.getMembers()) { // Type changed from LibraryMember to Person
			System.out.println(member.getFirstName());
			memberNames.add(member.getFirstName());
		}
		JFXComboBox jfxComboBox = new JFXComboBox();
		ObservableList<String> memberCollection = FXCollections.observableArrayList(memberNames);
		jfxComboBox.getItems().addAll(memberCollection);

		vBox.setMaxWidth(300);
		vBox.setMaxHeight(200);
		vBox.setMinWidth(300);
		vBox.setSpacing(20);
		vBox.getChildren().addAll(hBox, hBox1, jfxComboBox);

		content.setMaxWidth(350);
		content.setMinWidth(350);
		content.setBody(vBox);
		content.setPrefSize(600, 300);

		JFXButton button = new JFXButton("Confirm");
		button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
		button.setPrefHeight(32);
		button.setPrefWidth(100);
		button.setMinWidth(50);
		button.setStyle("-fx-background-radius: 5.0;" + "-fx-background-color: BLUE;" + "-fx-font-weight: 20.0;"
				+ "-fx-text-fill: white");
		content.setActions(button);
		anchorp = new StackPane();
		// StackPane stackPane1 = new StackPane();
		JFXDialog dialog = new JFXDialog(stackpaneBody, content, JFXDialog.DialogTransition.RIGHT, true);
		dialog.show();
		button.setOnAction(e -> {
			String name = jfxComboBox.getSelectionModel().getSelectedItem().toString();
			// LibraryMember m = dataAccess.getmember(name); // Abrha
			Person m = dataAccess.getmember(name); // Abrha
			Book b = book_table.getSelectionModel().getSelectedItem();
			checkOut(b, m);
			dialog.close();
		});

	}

	public void addNewBook(Book b, int copies) {
		// get book information from the users
		String ISBN = b.getISBN();
		Book book = dataAccess.getBook(ISBN);
		if (book == null) {
			b.addCopy(copies);
			dataAccess.addBook(b);
		} else {
			book.addCopy(copies);
			dataAccess.updateBook(book);
		}
	}

	public void checkOut(Book b, Person m) { // Abrha
		LocalDate dueDate = LocalDate.now(); // get selected date;
		BookCopy copy = b.getOneCopy();
		copy.setDueDate(dueDate);
		RecordEntry r = new RecordEntry(copy, m);
		this.dataAccess.addRecord(r);
		combo2.getSelectionModel().selectNext();

	}

	public void refreshBookTable() {
		books = FXCollections.observableArrayList(dataAccess.getBooks());
		book_table.setItems(books);
	}

	public void refreshMemberTable() {
		// Person m = dataAccess.getMembers().get(0);
		// System.out.println(m.getFirstName() + ":" + m.getAddress());
		members = FXCollections.observableArrayList(dataAccess.getMembers());
		memeber_table.setItems(members);
	}

}
