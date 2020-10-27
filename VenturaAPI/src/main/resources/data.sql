SET @TIME_IN_15_DAYS = DATEADD(DAY, +15, CURRENT_TIMESTAMP());
SET @TIME_YESTERDAY = DATEADD(DAY, -1, CURRENT_TIMESTAMP());

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(250)                                   NOT NULL,
    address       VARCHAR(250)                                   NOT NULL,
    phone_number  VARCHAR(250)                                   NOT NULL,
    document_id   VARCHAR(250)                                   NOT NULL,
    razao_social  VARCHAR(250)                                   NULL,
    email         VARCHAR(250)                                   NOT NULL,
    password      VARCHAR(250)                                   NOT NULL,
    type          ENUM ('candidato', 'empresa', 'administrador') NOT NULL,
    date_created  TIMESTAMP                                               DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO users (id, name, address, phone_number, document_id, razao_social, email, password,
                   type)
VALUES (1, 'Eliodoro Gonçalves Fonseca', 'Rua do Carmo, 305/256 - Botafogo - Rio de Janeiro',
        '11911111111', '09889009', '', 'eliodoro@fonseca.com', '123456', 'candidato'),
       (2, 'Francisa Alves', 'Av. Belarmindo Gomes, 400 sala B - Caxias', '11911736111', '3654464',
        '', 'franciscas@alves.com', '123456', 'candidato'),
       (3, 'Rômulo da Silva', 'Travessa Roque Santos, 286 casa 1 - São Pedro da Aldeia',
        '11911111111', '786989769', '', 'romulo@silva.com', '123456', 'candidato'),
       (4, 'Carolina Braga', 'Rua Raquel Matos, 444/304 - Centro - Rio de Janeiro', '11911118679',
        '123421234', '', 'carolina@braga.com', '123456', 'candidato'),
       (5, 'Paulo Barbosa', 'Alameda Santos, 288 casa 10 - Pinheiros - São Paulo', '11911155879',
        '8970808', '', 'paulo@barbosa.com', '123456', 'candidato'),
       (6, 'Santana Óleo e Gás', 'Av. Passos , 2222 - Centro - Rio de Janeiro', '11911111111',
        '3425555', 'Santana LTDA', 'rh@santanaoleoegas.com', '123456', 'empresa'),
       (7, 'SmartTrends', 'Av. Galega Silva, 88 - Leblon - Rio de Janeiro', '11911111111',
        '56867865878', 'SmartTrends LTDA', 'rh@smarttrends.com', '123456', 'empresa'),
       (8, 'B2Y Retail', 'Alameda Garrido, 77 - Belo Horizonte', '11911111111', '45635643564',
        'B2Y Retail LTDA', 'rh@2yretail.com', '123456', 'empresa'),
       (9, 'Hans Monen', 'Avenida Atlantica, 22/301 - Copacabana - Rio de Janeiro', '11911111111',
        '789679879', '', 'hans.monen@venturarh.com', '123456', 'administrador'),
       (10, 'Leandro Medina', 'Rua Andrade Rocha, 678/301 - Jardins - São Paulo', '11911111111',
        '456745675467', '', 'leandro.medina@venturarh.com', '123456',
        'administrador');

DROP TABLE IF EXISTS vagas CASCADE;
CREATE TABLE vagas
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    owner_id          INT                NOT NULL,
    short_description VARCHAR(250)       NOT NULL,
    long_description  VARCHAR(1000)       NOT NULL,
    city              VARCHAR(250)       NOT NULL,
    state             VARCHAR(250)       NOT NULL,
    type              ENUM ('CLT', 'PJ') NOT NULL,
    time_span         VARCHAR(250)       NOT NULL,
    date_expiration   TIMESTAMP          NOT NULL,
    date_created      TIMESTAMP                   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified     TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO vagas (id, owner_id, short_description, long_description, city, state, type, time_span,
                   date_expiration)
VALUES (1, 6, 'Gerente de Projetos Sênior', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?', 'Rio de Janeiro', 'RJ', 'CLT',
        '6 meses',
        @TIME_IN_15_DAYS),
       (2, 6, 'Engenheiro de Óleo e Gás', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Rio de Janeiro', 'RJ', 'PJ',
        'Permanente',
        @TIME_IN_15_DAYS),
       (3, 6, 'Recepcionista', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam vitae turpis arcu. Phasellus eu blandit urna. Sed sit amet leo at sem ornare efficitur. Vivamus eget facilisis elit. In semper nibh sed massa facilisis dapibus vel ac metus. Etiam non rutrum sem. Nullam volutpat quis nisi sit amet congue.', 'Vitória', 'ES', 'CLT', '1 ano',
        @TIME_YESTERDAY),
       (4, 7, 'Telemarketing', 'Nunc ornare et ipsum a consequat. Phasellus facilisis arcu vel ante semper iaculis id sit amet augue. Sed dictum est id egestas luctus. Donec dignissim massa nec bibendum cursus. Phasellus sapien lorem, scelerisque a nisl in, consectetur condimentum tortor. Fusce consequat quis elit eu malesuada. Nam rutrum lacus sit amet neque rutrum, non ultricies erat interdum. Fusce finibus elementum nulla auctor aliquet.', 'São Paulo', 'SP', 'PJ', '2 meses',
        @TIME_IN_15_DAYS),
       (5, 7, 'Desenvolvedor Front-End', 'Nulla tincidunt malesuada eros, vel tempus orci cursus non. Duis euismod neque vitae enim sagittis tincidunt. Morbi malesuada ipsum ut ipsum sollicitudin, eu euismod lectus suscipit. Sed tristique, enim luctus cursus dignissim, lacus enim gravida dolor, ac sollicitudin magna dui vel sapien. Praesent ac pretium neque. Mauris vel ipsum tincidunt turpis tincidunt viverra. Vestibulum egestas faucibus sem, sit amet sagittis augue ullamcorper ac. Praesent ut nunc dictum augue porta cursus.', 'São Paulo', 'SP', 'CLT',
        'Permanente', @TIME_IN_15_DAYS),
       (6, 7, 'Analista DevOps', 'Duis elementum est non orci efficitur, porttitor iaculis turpis fringilla. Donec imperdiet eros sit amet purus molestie ultricies. In justo justo, eleifend vel augue a, blandit ultrices justo. Suspendisse ultrices fermentum egestas.', 'Belo Horizonte', 'MG', 'PJ', 'Permanente',
        @TIME_YESTERDAY),
       (7, 8, 'Atendimento ao cliente', 'Nulla id ligula ut purus rhoncus dignissim. Nam venenatis felis eu ex consequat, et elementum lectus sagittis. Maecenas feugiat ante nunc, sit amet feugiat velit iaculis vitae. Vivamus varius tincidunt libero, quis placerat quam commodo quis. Integer ex enim, sollicitudin quis metus non, varius egestas erat. Nulla bibendum blandit varius.', 'Recife', 'PE', 'CLT', '6 meses',
        @TIME_IN_15_DAYS),
       (8, 8, 'Executivo financeiro', 'Maecenas malesuada aliquam neque, ut venenatis arcu faucibus vel. Vestibulum id tempus sapien. Nulla dignissim turpis lacus, at dictum ex tempus eu.', 'Fortaleza', 'CE', 'PJ', '6 meses',
        @TIME_YESTERDAY),
       (9, 9, 'Motorista', 'Duis scelerisque dignissim felis, at egestas libero luctus ut. Sed iaculis ipsum est, et consectetur erat viverra a. Quisque pharetra, tellus eu maximus tincidunt, ante libero fringilla magna, nec dignissim tortor lectus id ipsum. Donec at mattis justo. Etiam pulvinar interdum lacus ut consectetur. Duis id nisi augue. Aenean nisl nibh, fringilla et facilisis id, pulvinar nec libero. Integer at luctus ipsum, eu posuere est.', 'Belo Horizonte', 'MG', 'CLT', 'Permanente',
        @TIME_IN_15_DAYS);

DROP TABLE IF EXISTS respostas CASCADE;
CREATE TABLE respostas
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id       INT          NOT NULL,
    candidato_id  INT          NOT NULL,
    curriculo_url VARCHAR(250) NOT NULL,
    text_content  VARCHAR(1000) NOT NULL,
    date_created  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO respostas (id, vaga_id, candidato_id, curriculo_url, text_content)
VALUES (1, 1, 1, 'https://www.mockdomain.com/MockCurriculumUrl1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ac mattis sem. Suspendisse pellentesque urna non justo volutpat, sit amet facilisis nulla faucibus. Aenean tortor metus, egestas vel ultricies eu, luctus a arcu. Etiam pulvinar orci lectus, sed sollicitudin libero posuere in. Vestibulum sodales, lorem vitae volutpat fermentum, nulla ligula faucibus sapien, id volutpat augue leo non ante.'),
       (2, 2, 2, 'https://www.mockdomain.com/MockCurriculumUrl2', 'Donec ultrices urna dolor, nec porttitor velit fringilla nec. Fusce scelerisque luctus suscipit. Donec tortor est, aliquam sit amet venenatis nec, vulputate sed lectus.'),
       (3, 3, 1, 'https://www.mockdomain.com/MockCurriculumUrl3', 'Nunc faucibus id lorem sed mattis. Mauris tortor nunc, accumsan in ex a, vulputate feugiat quam. Mauris dignissim ipsum id magna malesuada vehicula. Fusce at imperdiet tellus. Morbi bibendum a sapien at placerat. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.'),
       (4, 4, 4, 'https://www.mockdomain.com/MockCurriculumUrl4', 'Aliquam malesuada non metus et ultrices. Mauris lacus turpis, ullamcorper vitae ex ut, sodales dapibus elit. Nunc sit amet ex ante. Nunc ac odio a neque tempor viverra vel sit amet sapien. Donec congue suscipit vehicula. Nulla non mi laoreet lectus maximus aliquam.'),
       (5, 5, 5, 'https://www.mockdomain.com/MockCurriculumUrl5', 'Proin tristique massa vulputate ex pretium, vel vulputate turpis suscipit. Etiam id mauris et ex accumsan dapibus. Sed commodo id mi at luctus.'),
       (6, 6, 1, 'https://www.mockdomain.com/MockCurriculumUrl1', 'Cras purus nisi, pellentesque quis commodo in, condimentum a est. Phasellus sodales dolor nec tellus euismod efficitur. Donec pulvinar metus at lorem congue scelerisque vitae a arcu. Donec convallis ipsum in sapien tristique, a lacinia dolor dapibus. Aenean vehicula accumsan massa, sit amet volutpat nulla.'),
       (7, 7, 2, 'https://www.mockdomain.com/MockCurriculumUrl2', 'Cras quis semper augue, nec tincidunt neque. Donec in elit tortor. Nulla gravida, urna vel interdum eleifend, elit risus ullamcorper libero, ut vulputate mi dui quis est. Integer ullamcorper vulputate sem eu elementum. Vestibulum dolor sapien, luctus at pharetra sed, lobortis eget ex.'),
       (8, 8, 1, 'https://www.mockdomain.com/MockCurriculumUrl3', 'Nam felis erat, porttitor ac tristique at, luctus ut metus. Sed accumsan tellus eu turpis molestie vulputate. Vestibulum sit amet sapien sem. Donec sem nunc, ullamcorper nec luctus sit amet, tincidunt vulputate orci. In eu congue dolor.'),
       (9, 9, 4, 'https://www.mockdomain.com/MockCurriculumUrl4', 'Donec velit nibh, eleifend eu nisi sed, elementum lobortis augue. In nec ultricies risus. Pellentesque cursus nibh nibh, et imperdiet tortor hendrerit eu. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.'),
       (10, 1, 2, 'https://www.mockdomain.com/MockCurriculumUrl2', 'Mauris ut orci eu tellus posuere auctor. Sed sollicitudin, nibh ut viverra hendrerit, dui ante blandit sapien, in venenatis justo libero ac mi. Phasellus aliquam ante ut ante pharetra ullamcorper. Etiam sed est placerat, interdum purus nec, interdum lorem.'),
       (11, 1, 3, 'https://www.mockdomain.com/MockCurriculumUrl3', 'Suspendisse ex nunc, interdum ac quam a, pharetra vestibulum augue. Curabitur sit amet fermentum urna. Ut consectetur dictum mollis. Suspendisse id laoreet nibh. Sed imperdiet, tellus quis molestie placerat, diam lectus hendrerit orci, vitae ornare magna tortor vel urna.');

DROP TABLE IF EXISTS resposta_criterio;
CREATE TABLE resposta_criterio
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    criterio_id   INT                                                          NOT NULL,
    resposta_id   INT                                                          NOT NULL,
    level         ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    date_created  TIMESTAMP                                                             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO resposta_criterio (id, criterio_id, resposta_id, level)
VALUES (1, 1, 1, 'baixo'),
       (2, 2, 1, 'medio'),
       (3, 3, 1, 'alto'),
       (4, 1, 10, 'muito baixo'),
       (5, 2, 10, 'medio'),
       (6, 3, 10, 'muito baixo'),
       (7, 1, 11, 'muito alto'),
       (8, 2, 11, 'muito alto'),
       (9, 3, 11, 'muito alto');

DROP TABLE IF EXISTS vaga_criterios;
CREATE TABLE vaga_criterios
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id       INT                                                          NOT NULL,
    name          VARCHAR(250)                                                 NOT NULL,
    description   VARCHAR(250)                                                 NOT NULL,
    pmd           ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    weight        ENUM ('muito baixo', 'baixo', 'medio', 'alto', 'muito alto') NOT NULL,
    position      INT                                                          NOT NULL,
    date_created  TIMESTAMP                                                             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified TIMESTAMP                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO vaga_criterios (id, vaga_id, name, description, pmd, weight, position)
VALUES (1, 1, 'MockCriteriaName01', 'MockDescription01', 'baixo', 'medio', 0),
       (2, 1, 'MockCriteriaName02', 'MockDescription02', 'medio', 'muito alto', 1),
       (3, 1, 'MockCriteriaName03', 'MockDescription03', 'muito baixo', 'muito baixo', 2);



