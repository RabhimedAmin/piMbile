<?php

namespace MobileBundle\Controller;

use EvenementBundle\Entity\AimerEvenement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class AimerEvenementController extends Controller
{
    public function findallAction()
    {
        $events = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:AimerEvenement')
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($events);
        return new JsonResponse($formatted);
    }

    /*
        public function findbyidAction($id)
        {
            $event = $this->getDoctrine()->getManager()
                ->getRepository('EvenementBundle:Evenement')
                ->find($id);
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($event);
            return new JsonResponse($formatted);
        }

        public function findbynomAction($nom)
        {
            $event = $this->getDoctrine()->getManager()->getRepository('EvenementBundle:Evenement')->findBy(array("nom"=>$nom));
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($event);
            return new JsonResponse($formatted);
        }
    */
    public function aimerAction($idevenement,$iduser)
    {
        $em = $this->getDoctrine()->getManager();
        $p = new AimerEvenement();



        $em1 = $this->getDoctrine()->getManager();
        $user=$em1->getRepository('AppBundle:User')->findOneBy(array("id"=>$iduser));
        $p->setIdUser($user);

        $em1 = $this->getDoctrine()->getManager();
        $evenement=$em1->getRepository('EvenementBundle:Evenement')->findOneBy(array("id"=>$idevenement));
        $p->setIdEvenement($evenement);

        $em->persist($p);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($p);
        return new JsonResponse($formatted);
    }


    public function aimerpasAction($idevenement,$iduser)
    {

        $em = $this->getDoctrine()->getManager();
        $participation=$em->getRepository("EvenementBundle:AimerEvenement")->findOneBy(array("id_user"=>$iduser,"id_evenement"=>$idevenement));


        $em2 = $this->getDoctrine()->getManager();
        $em2->remove($participation);
        $em2->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($participation);
        return new JsonResponse($formatted);

    }

}
