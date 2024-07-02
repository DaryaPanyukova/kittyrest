import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import CreateCatPage from './components/cat/CreateCatPage';
import CatListPage from "./components/cat/CatListPage";
import HomePage from "./components/general/HomePage";
import OwnersListPage from "./components/owner/OwnerListPage";
import CreateOwnerPage from "./components/owner/CreateOwnerPage";
import EditProfilePage from "./components/owner/EditProfilePage";
import Menu from "./components/general/Menu";
import LoginPage from "./components/general/LoginPage";
import {AuthProvider} from "./utils/AuthContext";
import SignUpPage from "./components/general/SignUpPage";
import OwnerPage from "./components/owner/OwnerPage";


function App() {
    return (
        <div>
            <AuthProvider>
                <Router>
                    <Menu/>
                    <div className="container">
                        <Routes>
                            <Route path="/" exact element={< HomePage/>}/>

                            <Route path="/add-cat" element={< CreateCatPage/>}/>
                            <Route path="/cats" element={<CatListPage/>}/>

                            <Route path="/owners" element={< OwnersListPage/>}/>
                            <Route path="/add-owner" element={< CreateOwnerPage/>}/>
                            <Route path="/owner/:ownerId" element={<OwnerPage />} />
                            <Route path="/editProfile/:ownerId" element={<EditProfilePage />} />

                            <Route path="/login" element={<LoginPage/>}/>
                            <Route path="/signUp" element={<SignUpPage/>}/>
                        </Routes>
                    </div>
                </Router>
            </AuthProvider>
        </div>
    );
}

export default App;