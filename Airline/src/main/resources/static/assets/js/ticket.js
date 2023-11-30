function addQrCode() {
  let qrWidth = window.innerWidth / 30;
  let qrcode = new QRCode(document.getElementById("qrcode"), {
    text: "https://google.com",
    width: qrWidth,
    height: qrWidth,
    correctLevel: QRCode.CorrectLevel.H
  });
}

function resizeQrCode() {
  let qr = document.getElementById("qrcode");
  qr.innerHTML = "";
  addQrCode();
}

addQrCode();
window.addEventListener("resize", resizeQrCode);