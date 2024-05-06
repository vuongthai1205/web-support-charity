import CharityShow from "./CharityShow";
import Donations from "./Donations";
import FormSignUp from "./FormSignUp";
import Gratitude from "./Gratitude";
import Introduce from "./Introduce";
import News from "./News";
import Slider from "./Slider";

function Home() {
  return ( <div className="main mt-[-76px]">
    <Slider/>
    <Introduce/>
    <CharityShow/>
    <Donations/>
    <Gratitude />
    <FormSignUp/>
    <News/>
  </div> );
}

export default Home;