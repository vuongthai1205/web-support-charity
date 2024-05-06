import Header from "../components/Header";
import Footer from "../components/Footer";
import Topbar from "../components/Topbar";
function HeaderOnly({ children }) {
  return (
    <>
      <Topbar />
      <Header />

      <div className="content">{children}</div>
      <Footer />
    </>
  );
}

export default HeaderOnly;
