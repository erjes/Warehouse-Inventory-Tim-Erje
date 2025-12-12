# Warehouse-Inventory-Tim-Erje

### Warehouse Inventory Application

### Java Swing + MySQL

### Dokumentasi Struktur Class dan Pembagian Tugas

---

# **1. Keterangan**

Dokumen ini menjabarkan seluruh **class**, **package** aplikasi _Warehouse Inventory_.
Java (MVC + DAO + Service).

# **2. Package (Struktur Folder)**

```

├── model          → POJO / entity classes
├── dao            → DAO interfaces
├── dao.impl       → DAO implementations (JDBC)
├── service        → Business logic services
├── util           → Utilities (DB connection, password hashing)
└── ui             → Java Swing UI (CRUD, transaction panels)
    └── component  → reusable UI components (masih opsional)
```

---

# **3. Team Responsibilities**

Pembagian tugas.

---

# **4. RADJA — Backend Core & Database Layer**

**Package namespaces:**
`model`,
`dao`,
`dao.impl`,
`util`

---

## **4.1 Model Classes (POJO)**

Lokasi: `model`

### **Role**

Fields:

- `int id`
- `String name`

### **User**

Fields:

- `int id`
- `String username`
- `String password`
- `int roleId`
- `Timestamp createdAt`

### **Warehouse**

Fields:

- `int id`
- `String name`
- `String location`

### **Item**

Fields:

- `int id`
- `int warehouseId`
- `String name`
- `String category`
- `BigDecimal price`
- `int quantity`
- `Timestamp createdAt`
- `Timestamp updatedAt`

### **Supplier**

Fields:

- `int id`
- `String name`
- `String contact`

### **Customer**

Fields:

- `int id`
- `String name`
- `String address`
- `String contact`

### **Transaction** _(single-item per transaksi)_

Fields:

- `int id`
- `int userId`
- `int itemId`
- `String transactionType`
- `Integer supplierId`
- `Integer customerId`
- `int quantity`
- `Timestamp transactionDate`
- `String note`

### **Report** _(opsional)_

Fields:

- `int id`
- `int userId`
- `Date reportDate`
- `String type`
- `String description`
- `String filePath`

---

## **4.2 Utility Classes**

Lokasi: `util`

### **DBConnection**

```
public static Connection getConnection() throws SQLException
```

- Membaca konfigurasi dari `db.properties`
- Mengelola koneksi MySQL (DriverManager)

### **PasswordUtil**

```
public static String hash(String plain);
public static boolean verify(String plain, String hash);
```

---

## **4.3 DAO Interfaces**

Lokasi: `dao`

### **UserDao**

```
User findById(int id);
User findByUsername(String username);
int create(User u);
int update(User u);
int delete(int id);
List<User> findByRole(int roleId);
```

### **ItemDao**

```
int create(Item item);
Item findById(int id);
List<Item> findAll();
List<Item> findByWarehouse(int warehouseId);
int update(Item item);
int updateQuantity(int itemId, int newQuantity, Connection conn);
int updateQuantityDelta(int itemId, int delta, Connection conn);
int delete(int id);
```

### **SupplierDao, CustomerDao, WarehouseDao**

CRUD sederhana:

```
create(), findById(), findAll(), update(), delete()
```

### **TransactionDao**

```
int create(Transaction t, Connection conn);
Transaction findById(int id);
List<Transaction> findByDateRange(Date start, Date end);
List<Transaction> findByUser(int userId);
List<Transaction> findByItem(int itemId);
```

### **ReportDao** _(opsional)_

CRUD + query by date.

---

## **4.4 DAO Implementations**

Lokasi: `dao.impl`

Implementasi masing-masing interface:

- `UserDaoImpl`
- `ItemDaoImpl`
- `SupplierDaoImpl`
- `CustomerDaoImpl`
- `WarehouseDaoImpl`
- `TransactionDaoImpl`
- `ReportDaoImpl`

**Catatan penting:**

- Gunakan `PreparedStatement`
- Jangan commit/rollback di DAO jika method menerima `Connection`
- Buat mapper `ResultSet → Model`

---

# **5. FAIZ — Business Logic Layer (Service)**

**Package:** `service`

---

## **5.1 AuthService**

```
public User login(String username, String password) throws AuthenticationException;
public void changePassword(int userId, String oldPlain, String newPlain);
public void createUser(User u, String plainPassword);
```

Uses:

- `UserDao`
- `PasswordUtil`

---

## **5.2 InventoryService**

**Menangani inbound, outbound, dan return dengan atomic transaction.**

### Method utama:

```
public int createInbound(int userId, int itemId, int supplierId, int quantity, String note)
public int createOutbound(int userId, int itemId, int customerId, int quantity, String note)
public int createReturn(int userId, int itemId, int customerId, int quantity, String note)
```

### Alur transaksi:

1. Buka koneksi
2. `conn.setAutoCommit(false)`
3. Insert ke `TransactionDao.create(t, conn)`
4. Update stok via `ItemDao.updateQuantityDelta(...)`
5. Validasi (untuk OUTBOUND)
6. Commit
7. Rollback bila gagal

### Method tambahan:

```
public List<Transaction> getTransactions(Date start, Date end);
```

---

## **5.3 ReportService** _(opsional)_

```
public File exportTransactions(Date start, Date end);
public List<Transaction> getTransactions(Date start, Date end);
```

---

# **6. FADHIL — User Interface (CRUD UI)**

**Package:** `ui`, `ui.component`

---

## **6.1 Frame / Panel Utama**

### **LoginFrame**

```
private void attemptLogin(); → AuthService.login(...)
```

### **MainFrame**

- Tab: Master Data, Transactions, Reports

### **ItemPanel**

```
void loadItems();
void showAddDialog();
void showEditDialog(Item item);
void deleteSelectedItem();
```

### **ItemFormDialog**

- Form input untuk Item
- Method:

```
Item collectInput();
void populate(Item item);
```

### **SupplierPanel, CustomerPanel, WarehousePanel**

CRUD analog:

```
loadData(), add(), edit(), delete()
```

---

## **6.2 UI Components**

Lokasi: `ui.component`

### **EntityTableModel<T>**

Custom `AbstractTableModel`.

### **SwingUtils**

Helper:

```
runInBackground(task, onSuccess, onError);
```

---

# **7. ATHA — Transaction UI + Functional Testing**

**Package:** `ui`

---

## **7.1 TransactionPanel**

Komponen:

- `cboType` (INBOUND / OUTBOUND / RETURN)
- `cboItem`
- `cboSupplier` / `cboCustomer`
- `txtQuantity`, `txtNote`
- `btnProcess`

Method:

```
void processTransaction(); // calls InventoryService.createXYZ()
void loadLookups();
```

---

## **7.2 TransactionHistoryPanel**

```
void loadRecentTransactions(); // uses TransactionDao.findByDateRange()
```

---

## **7.3 StockCheckDialog**

```
void lookupItemStock(int itemId); // uses ItemDao.findById()
```

---

# **8. Rangkuman**

## **Radja**

- Semua model class
- Semua DAO interface
- Implementasi DAO
- DBConnection & utils
- SQL schema
- Generate Report Menjadi File

## **Faiz**

- AuthService
- InventoryService
- ReportService
- Custom exceptions
- Unit test skeleton

## **Fadhil**

- LoginFrame
- MainFrame
- CRUD Panels
- CRUD Dialogs
- UI component helpers

## **Atha**

- TransactionPanel
- TransactionHistoryPanel
- StockCheckDialog
