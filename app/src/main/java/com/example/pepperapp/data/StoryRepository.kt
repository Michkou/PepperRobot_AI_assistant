package com.example.pepperapp.data

import com.example.pepperapp.R

data class Story(
    val id: Int,
    val title: String,
    val textContent: String,
    val backgroundResId: Int,
    val questions: List<Question> = emptyList()
)

data class Question(
    val text: String,
    val options: List<Pair<String, Int>>, // String label to Icon Resource ID
    val correctIndex: Int
)

object StoryRepository {
    fun getStoryById(id: Int): Story? {
        return stories.find { it.id == id }
    }

    private val stories = listOf(
        // Story 1: La moumoute du mammouth Helmouth (Old ID 2)
        Story(
            id = 1,
            title = "La moumoute du mammouth Helmouth",
            backgroundResId = R.drawable.backgroundmammouth,
            textContent = "Mamamouth et Papapouth étaient très ennuyés car, depuis sa naissance, leur fils Helmouth était adorable, mais il avait un grave défaut : \\pau=500\\ il n'avait pas de touffe de poils sur la tête... \\pau=800\\ C'était bien le seul mammouth au crâne lisse comme une bille !\n\n" +
                    "Alors, pendant des mois, Mamamouth et Papapouth eurent recours à un stratagème très malin : \\pau=400\\ la nuit, dans les marais, ils déterraient des mottes de terre et de longues herbes folles, qu'ils mettaient sur la tête de leur fiston. \\pau=600\\ De loin, les autres animaux pensaient qu'Helmouth était parfaitement poilu.\n\n" +
                    "Seul Picpic, \\pau=300\\ un petit oiseau piquebœuf à bec rouge, était au courant de ce secret. \\pau=500\\ Il vivait sur le dos d'Helmouth et mangeait les insectes qui venaient le parasiter. Quel bonheur d'habiter sur un garde-manger vivant avec nourriture à domicile, sans besoin d'aller chasser ! \\pau=500\\ Et Picpic n'avait cure de la tonsure de son ami.\n\n" +
                    "Mais un jour, \\pau=800\\ catastrophe ! \\vct=120\\ Une tempête emporta la fausse touffe d'Helmouth.\n\n" +
                    "Aussitôt, \\rspd=80\\ tous les animaux entourèrent le pauvre petit \\pau=800\\ et l'observèrent avec stupéfaction et mépris. \\rspd=100\\ Les mammoutheaux du troupeau se moquèrent de lui en pouffant bêtement : \\pau=500\\ \\vct=115\\ « Il est ridicule ! Il ne ressemble à rien ! » \\vct=100\\ \n\n" +
                    "Même Mamamouth et Papapouth se sentirent soudain gênés et se tinrent à l'écart. \\pau=600\\ Alors Helmouth fut \\rspd=80\\ \\vct=85\\ très triste \\pau=800\\ d'être la risée des mammouths et la honte de ses parents. \\rspd=100\\ \\vct=100\\ \n\n" +
                    "Avalant quelques petites mouches collées à la peau d'Helmouth, Picpic se redressa soudain, \\rspd=85\\ chagriné de sentir son ami si malheureux. \\rspd=100\\ En colère contre tous les animaux, il leur lança de sa petite voix aiguë : \\vct=125\\ « Vous êtes des imbéciles ! \\pau=400\\ Plutôt que de ricaner comme de sales gamins, vous feriez mieux de l'aider ! \\pau=500\\ Il faut trouver une moumoute pour Helmouth! » \\vct=100\\ \n\n" +
                    "Calmés par le petit mais autoritaire Picpic, tous les animaux prirent un air sérieux et responsable. \\pau=600\\ Ils se mirent à réfléchir en adultes...\n\n" +
                    "Soudain, \\pau=400\\ le cheval eut une idée : « Je vais lui prêter un peu de ma belle chevelure ! » \\pau=600\\ Mais, voyant un mammoutheau avec une longue crinière blanche qui lui cachait les yeux, tous les animaux éclatèrent de rire... \\pau=500\\ et le cheval fut très vexé.\n\n" +
                    "Le mouton s'approcha : « Je vais lui prêter un peu de ma magnifique laine ! » \\pau=600\\ Mais, voyant un mammoutheau tout frisé, tous les animaux explosèrent de rire... \\pau=500\\ et le mouton fut très vexé.\n\n" +
                    "Le renard s'avança : « Je vais lui prêter un peu de ma somptueuse fourrure! » \\pau=600\\ Mais, voyant un mammoutheau rouquin, tous les animaux furent pliés de rire... \\pau=500\\ et le renard fut très vexé.\n\n" +
                    "« Je vais lui prêter un peu de mes redoutables piquants ! », dit le porc-épic. \\pau=600\\ Mais, voyant un mammoutheau tout hérissé, tous les animaux s'étouffèrent de rire... \\pau=500\\ et le porc-épic fut très vexé.\n\n" +
                    "« Je vais lui prêter quelques-unes de mes majestueuses plumes! », s'exclama le paon. \\pau=600\\ Mais, voyant un mammoutheau avec un grand plumeau coloré sur la tête, tous les animaux tombèrent par terre de rire... \\pau=500\\ et le paon fut très vexé.\n\n" +
                    "\\vct=80\\ « Il n'y a que moi qui puisse apporter la solution! », \\vct=100\\ rugit le lion. \\pau=600\\ Mais, voyant un mammoutheau avec une crinière royale, tous les animaux... \\pau=800\\ se retinrent de rire, et même de glousser, car ils n'avaient pas envie de se faire dévorer.\n\n" +
                    "\\vct=110\\ « J'en ai marre ! » \\vct=100\\ , cria soudain Helmouth, à bout de nerfs. \\pau=500\\ « C'est vrai, ça tourne au grand n'importe quoi ! », s'impatienta Picpic.\n\n" +
                    "Mamamouth et Papapouth accoururent auprès de leur fiston : \\pau=500\\ « Nous, on t'aime comme tu es, \\pau=400\\ et ce n'est pas grave si tu es différent des autres ! »\n\n" +
                    "« Ils ont raison, \\pau=300\\ tu es toi-même, tu es unique, on t'aime comme ça et on va te le prouver ! », s'exclama Picpic.\n\n" +
                    "Tenant fermement un caillou très affûté dans son bec rouge, Picpic se mit à raser le dessus de la tête de Mamamouth, de Papapouth et de tous les animaux... \n\n" +
                    "\\pau=800\\ enfin, tous, sauf le lion, car Picpic n'avait pas envie de se faire déchiqueter.\n\n" +
                    "Du coup, très jaloux, tous les mammoutheaux voulurent la même coiffure qu'Helmouth. Finalement, tout le monde imita Helmouth, qui au départ ne ressemblait soi-disant à rien... et qui, sans le vouloir, avait lancé le top de la mode capillaire !\n\n" +
                    "Helmouth comprit alors que sa différence \\pau=500\\ était devenue \\pau=500\\ sa force.",
            questions = listOf(
                Question("Q1 : Comment se sent Helmouth quand les autres se moquent de lui ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 2),
                Question("Q2 : Comment se sent Picpic quand il voit Helmouth triste ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 0),
                Question("Q3 : Comment se sent le cheval quand les autres rient de lui ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 4),
                Question("Q4 : Comment se sent Helmouth quand il crie “J’en ai marre !” ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 0),
                Question("Q5 : Qui enlève la touffe de poils de Helmouth ?", listOf("Le vent" to R.drawable.emoji_vent, "Picpic" to R.drawable.emoji_oiseau, "Le lion" to R.drawable.emoji_lion, "Le cheval" to R.drawable.emoji_cheval), 0),
                Question("Q6 : Qu’est-ce que la tempête enlève sur la tête d’Helmouth ?", listOf("Un chapeau" to R.drawable.emoji_chapeau, "Une touffe" to R.drawable.emoji_poils, "Une couronne" to R.drawable.emoji_couronne, "Une casquette" to R.drawable.emoji_casquette), 1),
                Question("Q7 : Qui parle très fort avec une voix aiguë pour aider Helmouth ?", listOf("Picpic" to R.drawable.emoji_oiseau, "Le mouton" to R.drawable.emoji_mouton, "Le cheval" to R.drawable.emoji_cheval, "Le lion" to R.drawable.emoji_lion), 0),
                Question("Q8 : Avec quoi Picpic rase la tête des animaux ?", listOf("Un caillou" to R.drawable.emoji_caillou, "Des ciseaux" to R.drawable.emoji_ciseaux, "Une brosse" to R.drawable.emoji_brosse, "Un bâton" to R.drawable.emoji_baton), 0)
            )
        ),
        // Story 2: Le dessin d'Helmouth (Old ID 3)
        Story(
            id = 2,
            title = "Le dessin de Helmouth",
            backgroundResId = R.drawable.backgroundmammouth2,
            textContent = "Mamamouth et Papapouth étaient très fiers de leur fils Helmouth : il était gentil, intelligent... et un peu rêveur.\n\n" +
                    "Helmouth passait des heures à regarder les nuages et les herbes folles, comme si elles lui racontaient des secrets invisibles que lui seul pouvait entendre. Seul Picpic, le petit oiseau piquebœuf à bec rouge, comprenait vraiment ce que faisait Helmouth.\n\n" +
                    "Picpic vivait sur son dos et mangeait les insectes qui venaient le chatouiller. Quel bonheur d’habiter sur un garde-manger vivant, avec nourriture à domicile, sans avoir besoin d’aller chasser ailleurs ! Et Picpic, lui, se moquait bien que Helmouth soit différent.\n\n" +
                    "Un matin, Mamamouth annonça une nouvelle importante : « Aujourd’hui, tous les mammoutheaux du troupeau doivent participer au grand concours de dessin ! »\n\n" +
                    "Helmouth pâlit. Dessiner devant les autres mammoutheaux ? Quelle catastrophe... Les mammoutheaux étaient souvent moqueurs, très moqueurs, et Helmouth le savait bien.\n\n" +
                    "Au lieu de dire la vérité, il sourit maladroitement : « Oh, super… j’adore dessiner… »\n\n" +
                    "Picpic connaissait la vérité. Helmouth n’était pas sûr d’être doué pour quoi que ce soit, et encore moins pour quelque chose que tout le monde allait regarder.\n\n" +
                    "Le jour du concours, tous les animaux se rassemblèrent autour d’un grand rocher plat qui servait de table.\n\n" +
                    "Le cheval avait apporté du charbon noir. Le mouton avait frotté des baies pour faire du violet. Le renard avait trouvé de l’argile rouge. Le porc-épic prêtait ses piquants pour tracer des traits très fins. Et le paon, évidemment, faisait briller ses plumes comme s’il avait déjà gagné.\n\n" +
                    "Le lion arriva le dernier et déclara d’une voix grave : « On doit dessiner ce qu’on est. Je serai le jury. »\n\n" +
                    "Les mammoutheaux se mirent au travail. Certains dessinèrent de gros muscles. D’autres dessinèrent des défenses immenses. Un mammoutheau dessina même un mammouth couronné, parce que cela faisait sérieux.\n\n" +
                    "Helmouth, lui, resta immobile. Son charbon tremblait dans sa trompe. Il regardait les autres dessins et se sentait de plus en plus petit.\n\n" +
                    "Les mammoutheaux s’approchèrent et pouffèrent. « Ça ne ressemble à rien ! » « Il ne fait rien ! »\n\n" +
                    "Picpic avala une petite mouche et se redressa, furieux. « Vous êtes stupides ! Au lieu de vous moquer, vous devriez réfléchir ! »\n\n" +
                    "Calmés par le petit mais autoritaire Picpic, les animaux firent silence. Picpic murmura alors : « Helmouth, dessine le vent. Tu sais l’écouter. »\n\n" +
                    "Helmouth ferma les yeux. Il se souvint du son du vent : wouhouhoonu... Et sa trompe se mit à bouger toute seule. Il ne dessina pas un animal. Il dessina des spirales, des lignes courbes, des vagues légères. On aurait dit que le rocher respirait.\n\n" +
                    "Les animaux s’approchèrent, étonnés. Même le lion se tut.\n\n" +
                    "« C’est étrange… » murmura le mouton. « Ça bouge », dit le cheval. « C’est beau », admit le paon.\n\n" +
                    "Le lion déclara finalement : « C’est un dessin puissant. »\n\n" +
                    "Alors les mammoutheaux, vexés, voulurent tous faire pareil. Très vite, le rocher se couvrit de spirales.\n\n" +
                    "Mamamouth et Papapouth regardèrent Helmouth avec émotion.\n\n" +
                    "Helmouth comprit alors que sa façon différente de voir le monde était devenue une force.",
            questions = listOf(
                Question("Q1 : Comment se sent Helmouth quand les autres se moquent de lui ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 4),
                Question("Q2 : Comment se sent Picpic quand les autres se moquent d’Helmouth ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 0),
                Question("Q3 : Comment se sentent les parents d’Helmouth à la fin ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 3),
                Question("Q4 : Comment se sent Helmouth à la fin de l’histoire ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 3),
                Question("Q5 : Qui apporte du charbon noir pour dessiner ?", listOf("Le cheval" to R.drawable.emoji_cheval, "Le mouton" to R.drawable.emoji_mouton, "Le renard" to R.drawable.emoji_renard, "Le lion" to R.drawable.emoji_lion), 0),
                Question("Q6 : Qui dit qu’il sera le jury ?", listOf("Le lion" to R.drawable.emoji_lion, "Picpic" to R.drawable.emoji_oiseau, "Le cheval" to R.drawable.emoji_cheval, "Le mouton" to R.drawable.emoji_mouton), 0),
                Question("Q7 : Qui aide Helmouth à trouver une idée ?", listOf("Picpic" to R.drawable.emoji_oiseau, "Le lion" to R.drawable.emoji_lion, "Le cheval" to R.drawable.emoji_cheval, "Le mouton" to R.drawable.emoji_mouton), 0),
                Question("Q8 : Que dit Picpic à Helmouth de dessiner ?", listOf("Le vent" to R.drawable.emoji_vent, "Des fleurs" to R.drawable.emoji_fleur, "Une couronne" to R.drawable.emoji_couronne, "Un mammouth" to R.drawable.emoji_mammouth), 0)
            )
        ),
        // Story 3: Le mamouth Helmouth joue au foot (Old ID 4)
        Story(
            id = 3,
            title = "Le mamouth Helmouth joue au foot",
            backgroundResId = R.drawable.background_animals,
            textContent = "Helmouth était un mammoutheau très gentil et très intelligent. \\pau=500\\ Mais bizarrement, hormis son fidèle Picpic, le petit oiseau piquebœuf, il n'avait pas beaucoup d'amis.\n\n" +
                    "Helmouth passait son temps à rêvasser en regardant les herbes folles danser dans la brise. \\pau=500\\ Il aimait bien le son du vent, qui ressemblait parfois à une musique... \\pau=800\\ \\vct=120\\ wouhouhoonu! \\vct=100\\ \n\n" +
                    "Mamamouth et Papapouth commençaient à s'inquiéter un peu pour lui. \\pau=300\\ « Il ne joue pas beaucoup avec les autres, dit Mamamouth. Tu trouves ça normal ? » \\pau=500\\ « Helmouth n'a jamais été comme tout le monde, soupira Papapouth. Il doit être un peu timide... \\pau=400\\ On peut peut-être l'aider à se faire des camarades ? »\n\n" +
                    "Quelques jours plus tard, Mamamouth et Papapouth annoncèrent joyeusement à Helmouth qu'ils l'avaient inscrit au club de fouth ! \\pau=500\\ « Tu commences demain, au cours des mammoutheaux. C'est une belle surprise, non ? »\n\n" +
                    "Mais pour Helmouth, \\pau=600\\ \\vct=120\\ quelle catastrophe ! \\vct=100\\ Contrairement à tous les mammoutheaux de son âge, il détestait le fouth... \\pau=600\\ Mamamouth et Papapouth semblaient si contents qu'il n'osa pas les décevoir. « Oh, quelle bonne idée ! mentit-il. J'adore le fouth ! »\n\n" +
                    "Puis il rejoignit Picpic et lui raconta tout. « Tu pourrais simplement avouer à tes parents que tu n'aimes pas le fouth, et leur proposer de faire une autre activité à la place ? dit gentiment Picpic. » \\pau=500\\ \\rspd=85\\ \\vct=85\\ « C'est trop tard pour leur dire la vérité, \\rspd=100\\ \\vct=100\\ murmura Helmouth. Et je ne sais pas quel autre sport j'aimerais faire... »\n\n" +
                    "« Du patinage, de la natation, du saut à la perche, du parachute ? Ou du deltaplane ! Comme ça, tu pourrais voler avec moi ! » \\pau=500\\ Paniqué, Helmouth se dit qu'il serait ridicule... ce serait encore pire que le fouth! \\pau=400\\ « Je ne suis pas doué pour tout ça, dit-il un peu gêné. D'ailleurs, je ne sais même pas si je suis doué pour quoi que ce soit... » \\pau=500\\ « Tu as forcément du talent pour quelque chose. Un jour, tu découvriras quelle est cette chose...»\n\n" +
                    "Le lendemain, Mamamouth et Papapouth accompagnèrent Helmouth au club de fouth. Puis ils le laissèrent tout seul comme un grand avec ses nouveaux camarades mammoutheaux. « Merci ! leur dit Helmouth. Je vais bien m'amuser !»\n\n" +
                    "Voulant surveiller ce qui allait se passer, Picpic arriva et se percha sur un arbre. Le cours commença sur les indications de l'entraîneur, un grand singe roux, athlétique et dynamique.\n\n" +
                    "\\rspd=115\\ « Allez hop, hop, hop, on fait trois allers-retours jusqu'à la rivière en courant ! Et on lève les pattes! » \\rspd=100\\ Rapidement, Helmouth se retrouva distancé par les autres mammoutheaux. Il arriva bien après eux, essoufflé.\n\n" +
                    "« Ce que tu peux être lent ! se moqua l'un d'eux. Tu es croisé avec un escargot ? ricana un autre. » \\pau=600\\ Horrifié, Helmouth s'imagina avec une coquille et des yeux au bout des cornes !\n\n" +
                    "\\rspd=115\\ « Allez hop, hop, hop, reprit l'entraîneur, on saute sur place d'une patte sur l'autre, très vite ! Puis on se jette à plat ventre, on roule au sol et on remonte, très vite! Et on recommence dix fois ! » \\rspd=100\\ \\pau=500\\ Épuisé, Helmouth n'arrivait pas à suivre. Les mammoutheaux continuaient à se moquer de lui. \\vct=85\\ « Ce que tu peux être lourd! Quel boulet! Tu as avalé une enclume ? » \\vct=100\\ \n\n" +
                    "« Pour finir, on va faire un match », annonça l'entraîneur. Il désigna les deux meilleurs joueurs du cours, qui étaient aussi les plus prétentieux : « Toi et toi, vous serez capitaines et vous choisirez les membres de votre équipe tour à tour. »\n\n" +
                    "Tous les mammoutheaux furent appelés les uns après les autres par les deux chefs d'équipe... \\pau=800\\ sauf Helmouth qui resta seul, en dernier. \\pau=500\\ « Bon, soupira son capitaine, tu seras gardien de but, comme ça, tu n'auras pas à courir! »\n\n" +
                    "Dans les buts, Helmouth fut incapable d'arrêter les grosses noix de coco projetées maintes fois par ses adversaires. \\pau=1000\\ \\rspd=85\\ \\vct=85\\ « On a perdu à cause de toi ! \\pau=500\\ râlèrent les mammoutheaux de son équipe. \\pau=500\\ Trente-cinq à deux, la honte ! \\pau=800\\ Tu es nul ! » \\rspd=100\\ \\vct=100\\ \n\n" +
                    "\\vct=120\\ « Ça suffit ! \\vct=100\\ hurla Picpic en descendant de son arbre et en atterrissant sur le dos d'Helmouth. C'est vous qui êtes nuls! Viens Helmouth, ils sont méchants et stupides, ils ne te méritent pas ! »\n\n" +
                    "En rentrant chez lui, \\pau=1000\\ Helmouth se mit à pleurer. \\rspd=70\\ \\vct=80\\ pleurer... \\pau=1000\\ pleurer... \\rspd=100\\ \\vct=100\\ Très tristes, Mamamouth, Papapouth et Picpic ne parvenaient pas à le consoler. \\pau=600\\ \\vct=115\\ « Moi j'aime jouer AVEC les autres ! \\vct=100\\ cria Helmouth. \\vct=115\\ Pas CONTRE les autres ! » \\vct=100\\ \n\n" +
                    "En sanglotant, Helmouth souffla fort dans sa trompe. \\pau=500\\ Si fort que, soudain, un son étrange en sortit... \\pau=500\\ un joli son, un peu comme celui du vent qu'Helmouth aimait tant...\n\n" +
                    "« Mais dis donc, s'exclama Picpic, ce n'est pas une trompe... \\pau=600\\ c'est une trompette ! » \\pau=400\\ Picpic rassembla les animaux. « Et si on montait un groupe de rock autour d'Helmouth et sa trompette? » lança-t-il gaiement.\n\n" +
                    "Tous les animaux acceptèrent avec enthousiasme! Le paon accrocha des clochettes à ses plumes et les secoua en faisant la roue, le cheval entrechoqua ses sabots, le mouton souffla dans des feuilles, le porc-épic prit deux de ses pics pour jouer de la batterie sur une souche d'arbre, le renard accrocha des lianes sur un morceau de bois et en fit une guitare... \\pau=500\\ Et le lion, qui rêvait depuis longtemps d'être célèbre et admiré de tous, devint le chanteur du groupe ! Picpic, qui avait une fort jolie voix, l'accompagnait en tant que choriste...\n\n" +
                    "Sous la direction de Picpic, le groupe créa une série de chansons et répéta beaucoup. \\pau=400\\ Helmouth travailla dur pendant des heures chaque jour pour sortir de sa trompe des notes de plus en plus longues, de plus en plus hautes et de plus en plus basses.\n\n" +
                    "Attirés par la musique, des animaux arrivèrent de partout pour écouter le groupe. Bientôt le groupe partit en tournée dans toute la région et chanta son grand tube devant des foules de fans d'Helmouth :\n\n" +
                    "\\rspd=110\\ \\vct=115\\ Helmouth déteste le fouth ! Helmouth déteste le fouth ! Et aussi la choucrouth! Et le pâté en crouth! Helmouth aime la musique! Helmouth aime la musique! ZIC ZIC ZIC ZIC YEEAAAHHH!!! \\rspd=100\\ \\vct=100\\ \n\n" +
                    "À chaque solo de trompe d'Helmouth, le public se déchaînait ! \\pau=600\\ « Nous sommes très fiers de toi, dirent Mamamouth et Papapouth après un concert, lorsqu'Helmouth descendit de scène. Personne ne joue de la trompe comme toi! »\n\n" +
                    "« Le meilleur trompiste du monde ! » s'exclamèrent Picpic et les membres du groupe, en applaudissant Helmouth de toutes leurs forces.\n\n" +
                    "Épanoui et joyeux, Helmouth avait trouvé sa voie...",
            questions = listOf(
                Question("Q1 : Comment se sent Helmouth quand les autres se moquent de lui pendant l’entraînement ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 2),
                Question("Q2 : Comment se sent Picpic quand les mammoutheaux insultent Helmouth ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 0),
                Question("Q3 : Comment se sentent ses parents à la fin du concert ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 3),
                Question("Q4 : Comment se sent Helmouth à la fin de l’histoire ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 1),
                Question("Q5 : Qui se perche sur un arbre pour surveiller Helmouth ?", listOf("Picpic" to R.drawable.emoji_oiseau, "Le lion" to R.drawable.emoji_lion, "Le cheval" to R.drawable.emoji_cheval, "Le mouton" to R.drawable.emoji_mouton), 0),
                Question("Q6 : Qui donne les exercices au début du cours ?", listOf("Un singe" to R.drawable.emoji_singe, "Picpic" to R.drawable.emoji_oiseau, "Le lion" to R.drawable.emoji_lion, "Le cheval" to R.drawable.emoji_cheval), 0),
                Question("Q7 : Quel animal devient le chanteur du groupe ?", listOf("Le lion" to R.drawable.emoji_lion, "Picpic" to R.drawable.emoji_oiseau, "Helmouth" to R.drawable.emoji_mammouth, "Le renard" to R.drawable.emoji_renard), 0),
                Question("Q8 : Quel animal joue de la batterie avec ses pics ?", listOf("Le porc-épic" to R.drawable.emoji_porcepic, "Le cheval" to R.drawable.emoji_cheval, "Le mouton" to R.drawable.emoji_mouton, "Le renard" to R.drawable.emoji_renard), 0)
            )
        ),
        // Story 4: Helmouth a perdu sa confiance (Old ID 1)
        Story(
            id = 4,
            title = "Helmouth a perdu sa confiance",
            backgroundResId = R.drawable.background_animals,
            textContent = "Helmouth était un mammoutheau très gentil et très intelligent. Mais, hormis son fidèle Picpic, le petit oiseau piquebœuf à bec rouge, il n’avait pas beaucoup d’amis.\n\n" +
                    "Helmouth passait son temps à rêvasser en regardant les herbes folles danser dans la brise. Il aimait le son du vent, qui ressemblait parfois à une musique douce... wouhouhoonu...\n\n" +
                    "Depuis quelque temps, Helmouth avait l’impression d’avoir perdu quelque chose d’important. Pas un objet. Pas un chemin. Mais sa confiance.\n\n" +
                    "Il regardait les autres mammoutheaux courir, rire, se pousser. Et lui restait un peu à l’écart, sans savoir où poser ses pattes.\n\n" +
                    "Picpic le voyait bien. Du haut du dos d’Helmouth, il voyait tout : les insectes, les herbes… et les pensées dans ses yeux.\n\n" +
                    "« Tu réfléchis trop », dit Picpic. « Je réfléchis fort », répondit Helmouth. « C’est pareil », soupira Picpic.\n\n" +
                    "Mamamouth et Papapouth s’inquiétaient. « Il est différent », dit Mamamouth. « Et ce n’est pas un problème », répondit Papapouth.\n\n" +
                    "Un matin, Mamamouth annonça : « Aujourd’hui, on fait une grande traversée jusqu’aux rochers noirs. Tous les mammoutheaux viennent. »\n\n" +
                    "Helmouth sentit son ventre se serrer. Papapouth posa sa trompe sur son épaule. « Tu marcheras à côté de moi. »\n\n" +
                    "Le troupeau partit. Les mammoutheaux de son âge trottaient devant. « Regarde Helmouth ! » « Fais attention ! »\n\n" +
                    "Helmouth se tut. À l’intérieur, pourtant, ça faisait beaucoup de bruit.\n\n" +
                    "Plus loin, un petit mammoutheau tomba. Helmouth s’arrêta et l’aida à se relever. « J’ai honte », dit le petit. « Moi aussi parfois », répondit Helmouth.\n\n" +
                    "Le ciel devint gris. Une brume épaisse tomba sur la plaine. « Je ne vois plus », dit le cheval. « Moi non plus », murmura le mouton. « Suivez-moi ! » cria le renard. « Silence ! » rugit le lion. La peur grandit.\n\n" +
                    "Picpic murmura : « Écoute. Comme le vent. »\n\n" +
                    "Helmouth ferma les yeux. Il entendit les pas. Puis un autre bruit. Un glouglou régulier. « Il y a de l’eau », dit Helmouth.\n\n" +
                    "On hésita. Puis le sol craqua près du ruisseau. Mamamouth dit : « On l’écoute. »\n\n" +
                    "Helmouth guida le troupeau, lentement. Il levait la trompe quand l’eau se rapprochait. Il la baissait quand le chemin était sûr.\n\n" +
                    "La brume se déchira. Les rochers apparurent. Le troupeau souffla. « Tu nous as menés », dit Papapouth.\n\n" +
                    "Helmouth sentit sa confiance revenir, doucement. Il n’était pas devenu le plus fort. Il était devenu lui-même.",
            questions = listOf(
                Question("Q1 : Comment se sent Helmouth quand il voit les autres courir et rire sans lui ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 2),
                Question("Q2 : Comment se sent le petit mammoutheau qui tombe ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 2),
                Question("Q3 : Comment se sent Helmouth quand Papapouth dit : « Tu nous as menés » ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 3),
                Question("Q4 : Comment se sent Helmouth à la fin ?", listOf("En colère" to R.drawable.emoji_colere, "Content" to R.drawable.emoji_content, "Triste" to R.drawable.emoji_triste, "Fier" to R.drawable.emoji_fier, "Honteux" to R.drawable.emoji_honteux, "Amusé" to R.drawable.emoji_amuse), 1),
                Question("Q5 : Qui est le fidèle ami d’Helmouth ?", listOf("Picpic" to R.drawable.emoji_oiseau, "Le lion" to R.drawable.emoji_lion, "Le cheval" to R.drawable.emoji_cheval, "Le renard" to R.drawable.emoji_renard), 0),
                Question("Q6 : Qui aide le petit mammoutheau à se relever ?", listOf("Helmouth" to R.drawable.emoji_mammouth, "Picpic" to R.drawable.emoji_oiseau, "Le lion" to R.drawable.emoji_lion, "Le cheval" to R.drawable.emoji_cheval), 0),
                Question("Q7 : Quel animal rugit “Silence !” dans la brume ?", listOf("Le lion" to R.drawable.emoji_lion, "Picpic" to R.drawable.emoji_oiseau, "Helmouth" to R.drawable.emoji_mammouth, "Le renard" to R.drawable.emoji_renard), 0),
                Question("Q8 : Quel bruit Helmouth entend pour guider le troupeau ?", listOf("De l'eau" to R.drawable.emoji_eau, "Du feu" to R.drawable.emoji_feu, "Du vent fort" to R.drawable.emoji_vent, "Des arbres" to R.drawable.emoji_arbre), 0)
            )
        )
    )
}
