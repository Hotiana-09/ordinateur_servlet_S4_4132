# Prendre Liste Modele <= Base
++ 1 Servlet juste pour afficher Formulaire ( **ShowFormOrdiServlet.jsp**) :
    -- doGet() ihany no ilaina

# Modification
++ liste => icone modif => formulaire pres rempli 
    -- usage **ShowFormOrdiServlet.jsp** ihany mais mandefa id
        -- dans doGet() avahana par rapport existance ou non de l'id 

        -- doPost() 


# SUITE (misy am exam)
++ session
++ ajax

// React / Angular / VueJs


///////////////////////////////////////////////////////////////////////

# Suppression
++ liste => icone delete
    -- usage **OrdinateurServlet.jsp** ihany mais mandefa id
        -- doGet() 

# Session
++ Création table : 
    -- user 
        | id
        | login
        | mdp ( non crypté lony )
        | role (admin/ user/ null)

        ==> donnée <= inserer directement dans base

++ Création page : 
    -- login.jsp 
    
    -- LoginServlet.java
        | doPost()
            => checkLogin
                ==> true => session => /ordi
                ==> false => redirect login.jsp

    -- Modele : User.java
                    => boolean checkLogin(login, mdp)

#
    -- OrdinateurServlet.java
        \\ doGet()
            => ajout verification existance de la session ( avant ajout/delete/ modif )
                ==> false => redirect -> login

                          

