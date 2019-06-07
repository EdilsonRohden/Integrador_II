SELECT tipo_de_conta, totalizadores FROM (
SELECT p.id || '.' || p.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores,
	p.id AS p, 0 AS s, 0 AS m 
FROM 
	primeiro_nivel p 
	JOIN 
	conta c
	ON p.id = c.id_primeiro_nivel
	JOIN
	(SELECT * FROM movimentacao WHERE id_pessoa = 2 AND data_movimentacao BETWEEN '2019-06-01' AND '2019-06-30') AS m 
	ON m.id_conta = c.id_conta
GROUP BY 3, 4,5 

UNION

SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || ' ' || c.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores, 
	c.id_primeiro_nivel AS p, c.id_segundo_nivel AS s, 0 AS m
FROM 
	conta c 
	JOIN 
	(SELECT * FROM movimentacao WHERE id_pessoa = 2 AND data_movimentacao BETWEEN '2019-06-01' AND '2019-06-30') AS m 
	ON c.id_conta = m.id_conta
GROUP BY 1,3, 4, 5

UNION

SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || '.' || m.id_movimentacao || ' ' || m.descricao AS tipo_de_conta, m.valor_movimentacao AS totalizadores,
	c.id_primeiro_nivel AS p, c.id_segundo_nivel AS s, m.id_movimentacao AS m
FROM 
	conta c 
	JOIN 
	(SELECT * FROM movimentacao WHERE id_pessoa = 2 AND data_movimentacao BETWEEN '2019-06-01' AND '2019-06-30') AS m 
	ON c.id_conta = m.id_conta
ORDER BY 1, 2, 4, 5
) AS t1
ORDER BY p, s, m

