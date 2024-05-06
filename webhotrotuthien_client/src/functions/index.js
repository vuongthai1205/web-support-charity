export function formatDate(timestamp) {
  const date = new Date(timestamp);
  const day = date.getDate().toString().padStart(2, '0');
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Lưu ý rằng tháng bắt đầu từ 0
  const year = date.getFullYear();
  return `${day}-${month}-${year}`;
}

export function formatCurrency(amount) {
  // Sử dụng hàm toLocaleString để định dạng số và thêm VND
  return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}

export function formatDateForInput(dateString) {
  const parts = dateString.split('-');
  if (parts.length === 3) {
    // Chuyển đổi sang định dạng "yyyy-MM-dd"
    const formattedDate = `${parts[2]}-${parts[1]}-${parts[0]}`;
    return formattedDate;
  }
  return '';
}